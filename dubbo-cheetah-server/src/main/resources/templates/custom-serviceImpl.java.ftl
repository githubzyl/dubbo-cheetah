package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.common.BaseService;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service(version = InterfaceVersion.VERSION_1_0)
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends BaseService<${table.mapperName}, ${entity}> implements ${table.serviceName} {

}
</#if>
