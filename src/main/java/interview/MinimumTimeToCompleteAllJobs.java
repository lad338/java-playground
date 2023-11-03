package interview;

import java.util.*;

public class MinimumTimeToCompleteAllJobs {

    public static class Job {

        int time;
        List<Integer> dependencies;

        public Job(int time, List<Integer> dependencies) {
            this.time = time;
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

    public static void main(String[] args) {
        MinimumTimeToCompleteAllJobs solution = new MinimumTimeToCompleteAllJobs();

        System.out.println(
            solution.minimumTime(
                List.of(
                    new Job(5, Collections.emptyList()),
                    new Job(4, List.of(0)),
                    new Job(3, List.of(0, 1)),
                    new Job(1, Collections.emptyList()),
                    new Job(15, List.of(3, 2))
                )
            )
        );

        System.out.println(solution.cache);
    }
}
