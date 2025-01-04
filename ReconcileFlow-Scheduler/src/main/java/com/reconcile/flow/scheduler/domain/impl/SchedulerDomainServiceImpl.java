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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@ReconcileFlowService
public class SchedulerDomainServiceImpl implements SchedulerDomainService {

    @Resource
    private ReconcileFlowTransactionItemEntityRepository reconcileFlowTransactionEntityRepository;


    @Override
    @Transactional
    public void addOrUpdateTransaction(ReconcileFlowTxDTO txDTO) {
        ReconcileFlowTransactionItemEntity reconcileFlowTransactionItemEntity = new ReconcileFlowTransactionItemEntity();
        BeanUtil.copyProperties(txDTO, reconcileFlowTransactionItemEntity, CopyOptions.create().setIgnoreProperties("param"));
        reconcileFlowTransactionItemEntity.setParam(JSON.parseObject(txDTO.getParam(), new TypeReference<>() {
        }));
        reconcileFlowTransactionEntityRepository.save(reconcileFlowTransactionItemEntity);
    }

    @Override
    public void registerService(RegisterServiceDTO registerServiceDTO) {

    }
}
