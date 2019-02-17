package br.com.neuraltec.arduino2Serial;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class CommandArguments {
	protected Command commandObj;
	protected JsonObject argsOjb;
	protected String name;
	protected String value;
	public CommandArguments(Command _commandObj) {
		commandObj = _commandObj;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
}
