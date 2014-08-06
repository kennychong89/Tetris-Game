package com.androidgame.model;

import java.util.ArrayList;

public interface Observer {
	public void update(ArrayList<Integer> rows, ArrayList<Integer> columns, boolean filled);
}
