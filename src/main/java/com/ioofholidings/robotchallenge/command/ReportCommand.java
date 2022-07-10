package com.ioofholidings.robotchallenge.command;

import com.ioofholidings.robotchallenge.model.Table;
import com.ioofholidings.robotchallenge.service.TableService;

public class ReportCommand implements Command {

	@Override
	public void execute(Table table) {
		TableService.getInstance().print(table);
	}

}
