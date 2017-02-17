package com.github.twostone.leaderboard.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PartitioningSpliterator<E> extends AbstractSpliterator<List<E>>
{
  private final Spliterator<E> spliterator;
  private final int partitionSize;

  public PartitioningSpliterator(Spliterator<E> toWrap, int partitionSize) {
    super(toWrap.estimateSize(), toWrap.characteristics() | Spliterator.NONNULL);
    if (partitionSize <= 0) throw new IllegalArgumentException(
        "Partition size must be positive, but was " + partitionSize);
    this.spliterator = toWrap;
    this.partitionSize = partitionSize;
  }

  public static <E> Stream<List<E>> partition(Stream<E> in, int size) {
    return StreamSupport.stream(new PartitioningSpliterator<>(in.spliterator(), size), false);
  }

  @Override public boolean tryAdvance(Consumer<? super List<E>> action) {
    final ArrayList<E> partition = new ArrayList<>(partitionSize);
    while (spliterator.tryAdvance(partition::add) 
           && partition.size() < partitionSize);
    if (partition.isEmpty()) return false;
    action.accept(partition);
    return true;
  }

  @Override public long estimateSize() {
    final long est = spliterator.estimateSize();
    return est == Long.MAX_VALUE? est
         : est / partitionSize + (est % partitionSize > 0? 1 : 0);
  }
}