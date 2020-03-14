package com.onebooming.dao;


import com.onebooming.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-27 10:58
 * @ApiNote
 */
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
