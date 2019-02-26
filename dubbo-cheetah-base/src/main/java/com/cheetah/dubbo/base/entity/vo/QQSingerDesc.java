package com.cheetah.dubbo.base.entity.vo;

import com.cheetah.dubbo.common.utils.XmlUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/2/20 13:59
 */
@Data

public class QQSingerDesc implements Serializable {

    private final static String SUCCESS = "0";

    private static final long serialVersionUID = 1L;

    private String id;
    private String desc;
    private Map<String, String> basic;
    private Map<String, String> other;

    private String xml;

    public QQSingerDesc() {
    }


    public QQSingerDesc(String xml) {
        this.xml = xml;
    }


    @Override
    public String toString() {
        return "QQSingerDesc{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", basic=" + basic +
                ", other=" + other +
                '}';
    }

    public QQSingerDesc getInstance() throws DocumentException {
        QQSingerDesc entity = null;
        if (StringUtils.isNotBlank(xml)) {
            final Document doc = XmlUtil.getDocumentFromXmlStr(xml);
            final Element root = doc.getRootElement();
            final Element code = root.element("code");
            if (SUCCESS.equals(code.getText())) {
                final Element data = root.element("data");
                final Element info = data.element("info");
                entity = new QQSingerDesc();
                Element attr = info.element("id");
                if (null != attr && null != attr.getTextTrim()) {
                    entity.setId(attr.getText());
                }
                attr = info.element("desc");
                if (null != attr && null != attr.getText()) {
                    entity.setDesc(getHtmlStr("简介", XmlUtil.getCDATA(attr.getTextTrim())));
                }
                entity.setBasic(getMap(info,"basic"));
                entity.setOther(getMap(info,"other"));
            }
        }
        return entity;
    }

    public String toHtmlString(){
        StringBuilder sb = new StringBuilder();
        if(!CollectionUtils.isEmpty(this.basic)){
            this.basic.forEach((key, value) -> {
                sb.append(value);
            });
        }
        if(StringUtils.isNotBlank(this.desc)){
            sb.append(this.desc);
        }
        if(!CollectionUtils.isEmpty(this.other)){
            this.other.forEach((key, value) -> {
                sb.append(value);
            });
        }
        return sb.toString();
    }

    private String getHtmlStr(String key, String value) {
        return "<p>" + key + "：" + value + "</p>";
    }

    private Map<String,String> getMap(Element info, String attrName){
        Element attr = info.element(attrName);
        if(null != attr){
            Iterator iter = attr.elementIterator("item");
            Element item = null;
            Map<String, String> map = new HashMap<>();
            String key = null, value = null;
            while (iter.hasNext()) {
                item = (Element) iter.next();
                key = XmlUtil.getCDATA(item.elementTextTrim("key"));
                value = XmlUtil.getCDATA(item.elementTextTrim("value"));
                if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                    map.put(key, getHtmlStr(key, value));
                }
            }
            return map;
        }
        return null;
    }



}
