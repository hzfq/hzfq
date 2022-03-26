package top.hzfq.flowable.service;

import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.hzfq.flowable.util.FlowableConstant;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/28 21:39
 */
@SpringBootTest
public class FlowableModelServiceTest {

    @Resource
    private ModelService modelService;

    @Test
    public void createModel() {
        modelService.createModel(new Model(), FlowableConstant.createBy);
    }

    @Test
    public void getModel() {
        System.out.println(modelService.getModel("1"));
    }
}