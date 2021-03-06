package com.nstudiosappdev.core.data.datasource

import com.nstudiosappdev.core.model.DataHolder

interface DataSource {
    interface RemoteDataSource : DataSource {
        interface RequestDataSource<Req, Res : Any> : DataSource {
            suspend fun getResult(request: Req): DataHolder<Res>
        }

        interface FetchDataSource<Res : Any> : DataSource {
            suspend fun fetch(): DataHolder<Res>
        }
    }

    interface LocalDataSource<K, V> : DataSource {
        fun get(key: K): V?

        fun get(page: Int): List<V>

        fun get(key: String): List<V>

        fun get(key1: String, key2: String): V

        fun getAll(): List<V>

        fun put(key: K?, data: V): Boolean

        fun remove(value: V): Boolean

        fun removeByKey(key: K): Boolean

        fun remove(key1: String, key2: String): Boolean

        fun clear()
    }

    interface CacheDataSource<K, V> : DataSource {
        fun get(key: K): V?
        fun put(key: K, value: V): Boolean
        fun drop()
    }
}
