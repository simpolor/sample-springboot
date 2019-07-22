package io.simpolor.batch.item;

import org.springframework.batch.item.*;

import java.util.*;

public class QueueItemReader<T> implements ItemReader {

    private Queue<T> queue;

    public QueueItemReader(List<T> data){
        this.queue = new LinkedList<>(data);
    }


    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return queue.poll();
    }
}
