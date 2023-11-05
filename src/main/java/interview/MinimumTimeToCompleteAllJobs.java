package interview;

import java.util.*;

public class MinimumTimeToCompleteAllJobs {

    public static class Job {

        int time;
        int index;
        List<Integer> dependencies;

        public Job(int time, int index, List<Integer> dependencies) {
            this.time = time;
            this.index = index;
            this.dependencies = dependencies;
        }
    }

    public Map<Integer, Integer> cache = new HashMap<>();
    List<Job> jobs;

    public int minimumTime(List<Job> jobs) {
        this.jobs = jobs;

        for (int i = 0; i < jobs.size(); i++) {
            helper(i);
        }

        int result = Integer.MIN_VALUE;
        for (Integer key : cache.keySet()) {
            result = Math.max(result, cache.get(key));
        }

        return result;
    }

    private int helper(int jobId) {
        if (jobs.get(jobId).dependencies.isEmpty()) {
            return jobs.get(jobId).time;
        }

        if (cache.containsKey(jobId)) {
            return cache.get(jobId);
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < jobs.get(jobId).dependencies.size(); i++) {
            result =
                Math.max(result, helper(jobs.get(jobId).dependencies.get(i)));
        }

        cache.put(jobId, (jobs.get(jobId).time + result));
        return result;
    }

    public int minimumTimeIterative(List<Job> jobs) {
        int[] cache = new int[jobs.size()];

        Queue<Job> queue = new ArrayDeque<>(jobs);

        while (!queue.isEmpty()) {
            Job job = queue.poll();
            if (job.dependencies.size() == 0) {
                cache[job.index] = job.time;
                continue;
            }

            int max = 0;
            boolean notFound = false;
            for (int i = 0; i < job.dependencies.size() && !notFound; i++) {
                if (cache[job.dependencies.get(i)] != 0) {
                    max = Math.max(max, cache[job.dependencies.get(i)]);
                } else {
                    notFound = true;
                }
            }
            if (notFound) {
                queue.add(job);
                continue;
            }
            cache[job.index] = job.time + max;
        }

        return Arrays.stream(cache).max().orElse(-1);
    }

    public static void main(String[] args) {
        MinimumTimeToCompleteAllJobs solution = new MinimumTimeToCompleteAllJobs();

        List<Job> jobs = List.of(
            new Job(5, 0, Collections.emptyList()),
            new Job(4, 1, List.of(0)),
            new Job(3, 2, List.of(0, 1)),
            new Job(1, 3, Collections.emptyList()),
            new Job(15, 4, List.of(3, 2))
        );

        System.out.println(solution.minimumTime(jobs));

        System.out.println(solution.minimumTimeIterative(jobs));
    }
}
