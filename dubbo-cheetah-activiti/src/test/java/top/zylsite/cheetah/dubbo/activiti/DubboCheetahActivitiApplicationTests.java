package top.zylsite.cheetah.dubbo.activiti;

import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboCheetahActivitiApplicationTests {

    //获取引擎
    private ProcessEngine engine = null;
            
    
    @Before
    public void initEngine(){
        engine = ProcessEngines.getDefaultProcessEngine();
    }

    @Test
    public void contextLoads() {
        //存储服务
        RepositoryService repositoryService = engine.getRepositoryService();
        //运行时服务
        RuntimeService runtimeService = engine.getRuntimeService();
        //任务服务
        TaskService taskService = engine.getTaskService();

        Deployment deploy = repositoryService.createDeployment().addClasspathResource("processes/leave-1.bpmn").deploy();

        ProcessInstance myProcess = runtimeService.startProcessInstanceByKey("myProcess");

        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(myProcess.getProcessDefinitionId());

        Task task = taskQuery.singleResult();

        System.out.println("当前流程节点:" + task.getName());

        //员工填写完成请假的任务
        taskService.complete(task.getId());

        task = taskQuery.singleResult();

        System.out.println("当前流程节点:" + task.getName());

        taskService.complete(task.getId());

        task = taskQuery.singleResult();
        System.out.println("流程结束:" + task);

        engine.close();
        System.out.println("流程退出");
    }

    @Test
    public void testSave(){
        IdentityService identityService = engine.getIdentityService();
        for(int i = 0; i < 10 ; i++){
            Group group = identityService.newGroup(String.valueOf(i));
            group.setName("Group_"+i);
            group.setType("Type_"+i);
            identityService.saveGroup(group);
        }
    }

    @Test
    public void testQuery(){
        IdentityService identityService = engine.getIdentityService();
        GroupQuery query = identityService.createGroupQuery();
        //设置查询条件
        query.groupNameLike("Group_");
        //排序
        query.orderByGroupId().desc().orderByGroupName().asc();
        //统计总数
        long count = query.count();
        System.out.println("总条数："+count);
        //分页查询
        List<Group> list = query.listPage(0, 5);
        list.forEach((group) -> {
            System.out.println(group.getId() + "," + group.getName());
        });
    }


}
