package com.hdu.freeride.repository;

import com.hdu.freeride.entity.TransactionDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2018/1/4 20:19
 */
@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer>, JpaSpecificationExecutor<TransactionDetails> {


}
