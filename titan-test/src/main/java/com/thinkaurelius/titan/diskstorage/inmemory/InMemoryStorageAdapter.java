package com.thinkaurelius.titan.diskstorage.inmemory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.thinkaurelius.titan.diskstorage.StorageException;
import com.thinkaurelius.titan.diskstorage.keycolumnvalue.*;
import org.apache.commons.configuration.Configuration;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * (c) Matthias Broecheler (me@matthiasb.com)
 */

public class InMemoryStorageAdapter implements KeyColumnValueStoreManager {

    public InMemoryStorageAdapter(Configuration config) {

    }

    @Override
    public KeyColumnValueStore openDatabase(final String name) throws StorageException {
        return new KeyColumnValueStore() {
            @Override
            public boolean containsKey(ByteBuffer key, StoreTransaction txh) throws StorageException {
                return false;
            }

            @Override
            public List<Entry> getSlice(KeySliceQuery query, StoreTransaction txh) throws StorageException {
                return ImmutableList.of();
            }

            @Override
            public ByteBuffer get(ByteBuffer key, ByteBuffer column, StoreTransaction txh) throws StorageException {
                return null;
            }

            @Override
            public boolean containsKeyColumn(ByteBuffer key, ByteBuffer column, StoreTransaction txh) throws StorageException {
                return false;
            }

            @Override
            public void mutate(ByteBuffer key, List<Entry> additions, List<ByteBuffer> deletions, StoreTransaction txh) throws StorageException {
                //Do nothing
            }

            @Override
            public void acquireLock(ByteBuffer key, ByteBuffer column, ByteBuffer expectedValue, StoreTransaction txh) throws StorageException {
                //Do nothing
            }

            @Override
            public RecordIterator<ByteBuffer> getKeys(StoreTransaction txh) throws StorageException {
                return new RecordIterator<ByteBuffer>() {
                    @Override
                    public boolean hasNext() throws StorageException {
                        return false;
                    }

                    @Override
                    public ByteBuffer next() throws StorageException {
                        throw new NoSuchElementException();
                    }

                    @Override
                    public void close() throws StorageException {
                        //Do nothing
                    }
                };
            }

            @Override
            public ByteBuffer[] getLocalKeyPartition() throws StorageException {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void close() throws StorageException {
                //Do nothing
            }
        };
    }

    @Override
    public void mutateMany(Map<String, Map<ByteBuffer, KCVMutation>> mutations, StoreTransaction txh) throws StorageException {
        //Do nothing
    }

    @Override
    public StoreTransaction beginTransaction(ConsistencyLevel consistencyLevel) throws StorageException {
        return new StoreTransaction() {
            @Override
            public ConsistencyLevel getConsistencyLevel() {
                return ConsistencyLevel.DEFAULT;
            }

            @Override
            public void commit() throws StorageException {
                //Do nothing
            }

            @Override
            public void rollback() throws StorageException {
                //Do nothing
            }

            @Override
            public void flush() throws StorageException {
                //Do nothing
            }
        };
    }

    @Override
    public void close() throws StorageException {
        //Do nothing
    }

    @Override
    public void clearStorage() throws StorageException {
        //Do nothing
    }

    @Override
    public StoreFeatures getFeatures() {
        StoreFeatures f = new StoreFeatures();
        f.supportsScan=true;
        f.supportsBatchMutation=true;

        f.supportsTransactions=true;
        f.supportsConsistentKeyOperations=false;
        f.supportsLocking=true;

        f.isKeyOrdered=false;
        f.isDistributed=false;
        f.hasLocalKeyPartition=false;
        return f;
    }

    private final Map<String,String> config = Maps.newHashMap();

    @Override
    public String getConfigurationProperty(String key) throws StorageException {
        return config.get(key);
    }

    @Override
    public void setConfigurationProperty(String key, String value) throws StorageException {
        config.put(key,value);
    }
}
