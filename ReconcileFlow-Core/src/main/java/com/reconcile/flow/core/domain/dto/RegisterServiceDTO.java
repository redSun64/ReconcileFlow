package com.reconcile.flow.core.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @className: RegisterServiceDTO
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 17:41
 */
@Data
@Builder
public class RegisterServiceDTO {
    private String serviceName;
    private String address;
}