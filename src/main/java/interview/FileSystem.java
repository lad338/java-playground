package interview;

import java.util.*;

//Design a file system
//
//Given a file name, size and collection name
//
//Example:
//	file1.txt, 200, collection1
//	file2.txt, 300, collection2
//	file3.txt, 200, collection1
//	file4.txt, 500
//	file6.txt, 100, collection3
//
//- Get total size
//- Get top N collections sorted by file size

// Option 1: insert into when reading each file
// Complex
// M * ( N * Log N)

// Option 2: insert into heap after reading all files
// Simple
// M - number of files
//  M Log N

public class FileSystem {

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    static long size;
    int N;

    Map<String, FileCollection> hashMap = new HashMap<>();

    public FileSystem(int N) {}

    public void readFiles(List<File> files) {
        for (File file : files) {
            readFile(file);
        }
    }

    public void readFile(File file) {
        size += file.size;
        hashMap.putIfAbsent(
            file.collectionName,
            new FileCollection(file.collectionName)
        );
        hashMap.get(file.collectionName).totalSize += file.size;
    }

    public long getTotalSize() {
        return size;
    }

    public FileCollection[] getTopCollections(int N) {
        PriorityQueue<FileCollection> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a.totalSize)
        );
        for (String collectionName : hashMap.keySet()) {
            FileCollection current = hashMap.get(collectionName);

            if (!minHeap.isEmpty() && minHeap.size() == N) {
                int smallestFileSizeInHeap = minHeap.peek().totalSize;
                if (smallestFileSizeInHeap < current.totalSize) {
                    minHeap.poll();
                    minHeap.offer(current);
                }
            } else {
                minHeap.offer(current);
            }
        }

        FileCollection[] result = new FileCollection[N];
        int rightMostIndex = minHeap.size() - 1;
        while (!minHeap.isEmpty()) {
            result[rightMostIndex] = minHeap.poll();
            rightMostIndex--;
        }
        return result;
    }

    public static class File {

        public String name;
        public int size;
        public String collectionName;
    }

    public static class FileCollection {

        public FileCollection(String name) {
            this.name = name;
            this.totalSize = 0;
        }

        public int totalSize;
        public String name;
    }
    //- Get top N collections sorted by file size

}
