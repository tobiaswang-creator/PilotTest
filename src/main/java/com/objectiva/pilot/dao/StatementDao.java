package com.objectiva.pilot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.objectiva.pilot.model.Statement;

@Repository
@Mapper
public interface StatementDao {

	public List<Statement> selectAllStatements();

	public int addStatement(Statement statement);

	public Statement selectStatementByAccountId(String accountId);

}
