package ru.itmo.angry_beavers.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;

public class Entries implements Serializable {
	private final Deque<Entry> entries;
	private final SimpleDateFormat simpleDateFormat;
	
	public Entries() {
		entries = new ArrayDeque<>();
		simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	}
	
	public Deque<Entry> getEntries() {
		return entries;
	}
	
	public void addEntry(Entry entry) {
		entries.addFirst(entry);
	}
	
	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}
}
