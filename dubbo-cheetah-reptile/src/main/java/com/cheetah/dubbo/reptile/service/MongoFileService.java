package com.cheetah.dubbo.reptile.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.MongoFile;
import com.cheetah.dubbo.base.mapper.MongoFileMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.CustomFileUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/2/1 10:50
 * @Version v1.0
 */
@Service
public class MongoFileService extends SuperServiceImpl<MongoFileMapper, MongoFile> {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Resource
    private MongoDbFactory mongoDbFactory;

    @Value("${spring.data.mongodb.grid-fs-database}")
    private String gridFsDB;

    public MongoFile upload(String filePath, Long mainId, String fileName, String contentType) {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (null == ins) {
            return null;
        }
        ObjectId objectId = null;
        if (StringUtils.isBlank(contentType)) {
            objectId = gridFsTemplate.store(ins, fileName);
        } else {
            objectId = gridFsTemplate.store(ins, fileName, contentType);
        }
        String fileId = objectId.toString();
        MongoFile fileInfo = this.saveOrUpdate(mainId,fileId);
        return fileInfo;
    }

    public void download(Long mainId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MongoFile fileInfo = this.getMongoFile(mainId);
        if(null == fileInfo || null == fileInfo.getFileId()){
            return;
        }
        this.download(fileInfo.getFileId(),request,response);
    }

    public void download(String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GridFsResource gridFsResource = this.getFile(fileId);
        String fileName = gridFsResource.getFilename().replace(",", "");
        CustomFileUtil.downloadFile(fileName, gridFsResource.getInputStream(), request, response);
    }

    public GridFsResource getFile(String fileId){
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        GridFSFile gfsFile = gridFsTemplate.findOne(query);
        if(null == gfsFile){
            return null;
        }
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDbFactory.getDb(gridFsDB));
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(gfsFile.getObjectId());
        return new GridFsResource(gfsFile,in);
    }

    public boolean isExistFile(Long mainId){
        MongoFile fileInfo = this.getMongoFile(mainId);
        if(null == fileInfo){
            return false;
        }
        GridFsResource gridFsResource = this.getFile(fileInfo.getFileId());
        return null == gridFsResource ? false : true ;
    }

    public MongoFile saveOrUpdate(Long mainId, String fileId) {
        MongoFile fileInfo = this.getMongoFile(mainId);
        if (null == fileInfo) {
            fileInfo = new MongoFile();
        }
        fileInfo.setFileId(fileId);
        fileInfo.setMainId(mainId);
        this.saveOrUpdate(fileInfo);
        return fileInfo;
    }

    public MongoFile getMongoFile(Long mainId) {
        QueryWrapper<MongoFile> wrapper = new QueryWrapper<>();
        wrapper.eq(MongoFile.FIELD_MAIN_ID, mainId);
        return this.getOne(wrapper);
    }

}
