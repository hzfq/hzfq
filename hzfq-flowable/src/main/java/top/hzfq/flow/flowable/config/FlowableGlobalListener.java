package top.hzfq.flow.flowable.config;

import org.flowable.common.engine.api.delegate.event.*;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

/**
 * Flowable全局监听器
 *
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 17:33
 */
@Configuration
public class FlowableGlobalListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private SpringProcessEngineConfiguration configuration;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FlowableEventDispatcher dispatcher = configuration.getEventDispatcher();
        //注册监听器并指定监听节点
        dispatcher.addEventListener(new AbstractFlowableEngineEventListener() {
            @Override
            protected void taskCreated(FlowableEngineEntityEvent event) {
                super.taskCreated(event);
            }
        }, FlowableEngineEventType.TASK_CREATED);
    }
}
