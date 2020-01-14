package com.aidou.api;

import java.util.Date;

public class DueUserStatus {
    private String orderId;

    private String name;

    private String idcard;

    private Boolean isOverdue;

    private Integer collectionStatus;

    private Integer compensationStatus;

    private Boolean clearStatus;

    private String bank;

    private String partnerId;

    private String partnerName;

    private Long contractPrincipal;

    private Date createdTime;

    private String creator;

    private Date updateTime;

    private String updator;

    private String companyId;

    private Integer status;

    private String bankUpdated;

    private Long businessEmployeeId;

    private String businessEmployeeName;

    private String continuousDefault;

    private String cumulativeDefault;

    private Long amountRepaid;

    private String mobile;

    private Integer periodsNum;

    private Long stagingPrincipal;

    private Long installmentFee;

    private Long payPrincipal;

    private Long remainingPrincipal;

    private Long balance;

    private String partnerCode;

    private String loanDay;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Boolean getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public Integer getCompensationStatus() {
        return compensationStatus;
    }

    public void setCompensationStatus(Integer compensationStatus) {
        this.compensationStatus = compensationStatus;
    }

    public Boolean getClearStatus() {
        return clearStatus;
    }

    public void setClearStatus(Boolean clearStatus) {
        this.clearStatus = clearStatus;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public Long getContractPrincipal() {
        return contractPrincipal;
    }

    public void setContractPrincipal(Long contractPrincipal) {
        this.contractPrincipal = contractPrincipal;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankUpdated() {
        return bankUpdated;
    }

    public void setBankUpdated(String bankUpdated) {
        this.bankUpdated = bankUpdated == null ? null : bankUpdated.trim();
    }

    public Long getBusinessEmployeeId() {
        return businessEmployeeId;
    }

    public void setBusinessEmployeeId(Long businessEmployeeId) {
        this.businessEmployeeId = businessEmployeeId;
    }

    public String getBusinessEmployeeName() {
        return businessEmployeeName;
    }

    public void setBusinessEmployeeName(String businessEmployeeName) {
        this.businessEmployeeName = businessEmployeeName == null ? null : businessEmployeeName.trim();
    }

    public String getContinuousDefault() {
        return continuousDefault;
    }

    public void setContinuousDefault(String continuousDefault) {
        this.continuousDefault = continuousDefault == null ? null : continuousDefault.trim();
    }

    public String getCumulativeDefault() {
        return cumulativeDefault;
    }

    public void setCumulativeDefault(String cumulativeDefault) {
        this.cumulativeDefault = cumulativeDefault == null ? null : cumulativeDefault.trim();
    }

    public Long getAmountRepaid() {
        return amountRepaid;
    }

    public void setAmountRepaid(Long amountRepaid) {
        this.amountRepaid = amountRepaid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getPeriodsNum() {
        return periodsNum;
    }

    public void setPeriodsNum(Integer periodsNum) {
        this.periodsNum = periodsNum;
    }

    public Long getStagingPrincipal() {
        return stagingPrincipal;
    }

    public void setStagingPrincipal(Long stagingPrincipal) {
        this.stagingPrincipal = stagingPrincipal;
    }

    public Long getInstallmentFee() {
        return installmentFee;
    }

    public void setInstallmentFee(Long installmentFee) {
        this.installmentFee = installmentFee;
    }

    public Long getPayPrincipal() {
        return payPrincipal;
    }

    public void setPayPrincipal(Long payPrincipal) {
        this.payPrincipal = payPrincipal;
    }

    public Long getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(Long remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode == null ? null : partnerCode.trim();
    }

    public String getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(String loanDay) {
        this.loanDay = loanDay == null ? null : loanDay.trim();
    }
}