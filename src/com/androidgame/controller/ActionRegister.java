package com.androidgame.controller;

import com.androidgame.model.enums.Actions;

public interface ActionRegister {
	public void register(ActionReciever reciever);
	public void unregister(ActionReciever reciever);
	public void notifyRecievers(Actions action);
}
