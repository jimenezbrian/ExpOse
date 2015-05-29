package edu.allegheny.expose.tune;

import java.util.*;

import edu.allegheny.expose.tune.josephus.JosephusExperiment;
import edu.allegheny.expose.tune.simplealgs.LinLogExperiment;
import edu.allegheny.expose.tune.sort.SortingExperiment;

public class DefaultSuite extends BenchMarkSuite {

    private ArrayList<BenchMark> benchmarks;

    /**
     * Stores the number of benchmarks provided
     */
    private int size;

    public DefaultSuite(){
        super(new String[] {});
    }

    public DefaultSuite(String[] args){
        super(args);
    }

    public Iterator<BenchMark> iterator(){
        initializeBenchmarks();
        return new Iterator<BenchMark>() {
            public BenchMark next(){
                return benchmarks.remove(0);
            }
            public boolean hasNext(){
                return !benchmarks.isEmpty();
            }
			@Override
			public void remove() {
				throw new java.lang.UnsupportedOperationException();
			}
        };
    }

    public int size(){
        return size;
    }

    private void initializeBenchmarks(){
        size = 0;
        benchmarks = new ArrayList<BenchMark>();

        benchmarks.add(new JosephusExperiment(getArgs()));
        size++;
        // get sorting algorithms
        for (String sortAlg : SortingExperiment.algs){
            benchmarks.add(new SortingExperiment(getArgs(),sortAlg));
            size++;
        }
        // get simple algorithms
        for (int alg : LinLogExperiment.algs){
            benchmarks.add(new LinLogExperiment(getArgs(),alg));
            size++;
        }
    }
}
