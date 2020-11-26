package cn.liontalk.mysql2file.common;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

public class CommonItemReader<T> implements ItemReader<T> {

    private  Iterator<T> iterator;

    public CommonItemReader(List<T> data) {
        this.iterator = data.iterator();
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (iterator.hasNext()) {
            return this.iterator.next();
        } else {
            return null;
        }
    }
}
