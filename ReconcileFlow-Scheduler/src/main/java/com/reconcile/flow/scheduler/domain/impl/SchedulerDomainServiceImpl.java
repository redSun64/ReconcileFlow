package com.reconcile.flow.scheduler.domain.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.reconcile.flow.core.annotation.ReconcileFlowService;
import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;
import com.reconcile.flow.core.domain.dto.RegisterServiceDTO;
import com.reconcile.flow.core.domain.service.SchedulerDomainService;
import com.reconcile.flow.scheduler.entity.ReconcileFlowTransactionItemEntity;
import com.reconcile.flow.scheduler.repository.ReconcileFlowTransactionItemEntityRepository;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Service
@ReconcileFlowService
public class SchedulerDomainServiceImpl implements SchedulerDomainService {

    @Resource
    private ReconcileFlowTransactionItemEntityRepository reconcileFlowTransactionEntityRepository;


    @Override
    @Transactional
    public Long addOrUpdateTransaction(ReconcileFlowTxDTO txDTO) {
        ReconcileFlowTransactionItemEntity existEntity = null;
        if (Objects.nonNull(txDTO.getId())) {
            existEntity = reconcileFlowTransactionEntityRepository
                    .findById(txDTO.getTxId())
                    .orElse(null);
        }
        ReconcileFlowTransactionItemEntity reconcileFlowTransactionItemEntity = Objects.nonNull(existEntity) ? existEntity : new ReconcileFlowTransactionItemEntity();
        BeanUtil.copyProperties(txDTO, reconcileFlowTransactionItemEntity, CopyOptions.create().setIgnoreProperties("param"));
        reconcileFlowTransactionItemEntity.setParam(JSON.parseObject(txDTO.getParam(), new TypeReference<>() {
        }));
        return reconcileFlowTransactionEntityRepository
                .save(reconcileFlowTransactionItemEntity)
                .getId();
    }

    @Override
    public void registerService(RegisterServiceDTO registerServiceDTO) {

    }
}
