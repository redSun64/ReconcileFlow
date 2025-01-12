package com.reconcile.flow.scheduler.management;

import com.reconcile.flow.core.constants.ReconcileFlowConstants;
import com.reconcile.flow.scheduler.entity.ReconcileFlowTransactionItemEntity;
import com.reconcile.flow.scheduler.repository.ReconcileFlowTransactionItemEntityRepository;
import com.xxl.rpc.core.remoting.invoker.annotation.XxlRpcReference;
import com.xxl.rpc.core.remoting.invoker.generic.XxlRpcGenericService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: ReconcileFlowServiceManagement
 * @Description: manage reconcile flow service
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 16:53
 */
@Component
@Slf4j
public class ReconcileFlowServiceManagement {

    @XxlRpcReference
    private XxlRpcGenericService xxlRpcGenericService;

    @Resource
    private ReconcileFlowTransactionItemEntityRepository reconcileFlowTransactionItemEntityRepository;

    private Map<String, String> serviceMap;

    public ReconcileFlowServiceManagement() {
        serviceMap = new ConcurrentHashMap<>();
    }


    private boolean executeMethod(String className, String serviceName, String methodName, List<Map<String, Object>> params) {
        String[] paramTypes = new String[params.size()];
        Object[] paramValues = new Object[params.size()];
        for (int i = 0; i < params.size(); i++) {
            paramTypes[i] = params.get(i).keySet().stream().findFirst().orElseThrow();
            //todo 此处需要判断paramTypes的类型，然后转换为对应的类型，先写死，后续需要优化
            Object value = params.get(i).values().stream().findFirst().orElseThrow();
            if (paramTypes[i].contains("Long")) {
                value = NumberUtils.toLong(value.toString());
            }
            paramValues[i] = value;
        }
        xxlRpcGenericService.invoke(className, null, methodName, paramTypes, paramValues);
        return true;
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void queryNeedFixTx() {
        log.info("query need fix tx, current time: {}", Instant.now());
        List<ReconcileFlowTransactionItemEntity> needFixTxs =
                reconcileFlowTransactionItemEntityRepository.findByExpectedFinishDurationLessThanAndStatusIn(
                        Instant.now(),
                        List.of(ReconcileFlowConstants.WAIT, ReconcileFlowConstants.FAIL));

        needFixTxs.forEach(
                tx -> {
                    // 执行对应的服务方法
                    boolean executeResult = executeMethod(
                            tx.getClassName(),
                            tx.getServiceName(),
                            tx.getMethodName(),
                            tx.getParam()
                    );
                    if (executeResult) {
                        tx.setStatus(ReconcileFlowConstants.SUCCESS);
                        reconcileFlowTransactionItemEntityRepository.save(tx);
                    }
                });
    }
}