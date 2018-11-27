package com.cheetah.dubbo.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.common.SolrConstant;
import com.cheetah.dubbo.api.service.ISolrService;

@Service(version = InterfaceVersion.VERSION_1_0)
public class SolrServiceImpl implements ISolrService {

	@Autowired
	private SolrClient client;

	@Override
	public Object search(int page, int pageSize) throws Exception {
		try {
			SolrQuery params = new SolrQuery();
			// 查询条件, 这里的 q 对应 下面图片标红的地方
			params.set(SolrConstant.QUERY_CONDITION_Q, "appName:invest");
			// 过滤条件
			// params.set("fq", "product_price:[100 TO 100000]");
			// 排序
			params.addSort("updateTime", SolrQuery.ORDER.desc);
			// 分页
			params.setStart(page);
			params.setRows(pageSize);
			// 默认域
			params.set(SolrConstant.QUERY_CONDITION_DF, "appName");
			// 只查询指定域
			params.set(SolrConstant.QUERY_CONDITION_FL, "id,appName,updateTime,serverIp");
			// 高亮
			// 打开开关
			params.setHighlight(true);
			// 指定高亮域
			params.addHighlightField("appName");
			// 设置前缀
			params.setHighlightSimplePre("<span style='color:red'>");
			// 设置后缀
			params.setHighlightSimplePost("</span>");

			QueryResponse queryResponse = client.query("logger", params);
			SolrDocumentList results = queryResponse.getResults();
			long numFound = results.getNumFound();

			Map<String, Object> data = new HashMap<>();
			data.put("total", numFound);
			data.put("currPage", page + 1);
			data.put("data", queryResponse.getHighlighting());

			// 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
