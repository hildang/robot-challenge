package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.model.Table;
import com.ioofholidings.robotchallenge.service.TableService;

public abstract class Command {

	private TableService tableService; 
	
	public abstract Object execute(Table table);
	
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public TableService getTableService() {
		return this.tableService;
	}
}
