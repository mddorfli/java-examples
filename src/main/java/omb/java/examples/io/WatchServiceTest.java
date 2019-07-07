package omb.java.examples.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchServiceTest {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("E:\\temp");
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		while (true) {
			try {
				WatchKey key = watchService.take();
				pollEvents(key);
				key.reset();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void pollEvents(WatchKey watchKey) {
		List<WatchEvent<?>> events = watchKey.pollEvents();
		System.out.println(events.size()+" events: ");
		for (WatchEvent<?> event : events) {
			System.out.println(event.count() + " " + event.kind().name() + " happened to " + event.context());
		}
	}
}
