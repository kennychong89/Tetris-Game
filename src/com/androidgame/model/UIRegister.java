package com.androidgame.model;

public interface UIRegister {
	public void register(TetrisUI_Listener listener);
	public void unregister(TetrisUI_Listener listener);
	public void notifyListeners();
}
