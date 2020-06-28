package com.github.zzt93.syncer.data;

import com.github.zzt93.syncer.data.es.Filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Config operation interface
 * @author zzt
 */
public interface SyncData {

  Object getId();

  SyncData setId(Object id);

  String getEntity();

  boolean isWrite();

  /**
   * @deprecated {@link #updated()} might be better
   * @return whether this event type is {@link SimpleEventType}
   */
  boolean isUpdate();

  boolean isDelete();

  SyncData toWrite();

  SyncData toUpdate();

  SyncData toDelete();

  SyncData setEntity(String entity);

  String getRepo();

  SyncData setRepo(String repo);

  SimpleEventType getType();

  /**
   * Default use id as partitionKey, override with this method.
   *
   * @param fieldName name of field which is either int/long or String
   */
  void setPartitionField(String fieldName);

  SyncData addExtra(String key, Object value);

  SyncData addField(String key, Object value);

  SyncData setFieldNull(String key);

  SyncData renameField(String oldKey, String newKey);

  Object removeField(String key);

  SyncData removeFields(String... keys);

  boolean containField(String key);

  SyncData updateField(String key, Object value);

  HashMap<String, Object> getFields();

  HashMap<String, Object> getExtras();

  Object getField(String key);

  Long getFieldAsLong(String key);

  Integer getFieldAInt(String key);

  String getEventId();

  SyncData setSourceIdentifier(String identifier);

  String getSourceIdentifier();

  HashMap<String, Object> getSyncBy();

  /**
   * Call this method to update/delete/write by query
   * @return one instance for this SyncByQuery
   * @see SyncByQuery#syncBy(String, Object) add filter and will set id as null automatically
   */
  SyncByQuery syncByQuery();

  ESScriptUpdate esScriptUpdate();

  ESScriptUpdate esScriptUpdate(String script, Map<String, Object> params);

  /**
   * @param docFilter filter for query a ES doc and apply script, field value will be removed
   */
  ESScriptUpdate esScriptUpdate(Filter docFilter);

  ExtraQuery extraQuery(String indexName, String typeName);

  Object getExtra(String key);

  /**
   * create a new instance with meta info copied
   * @param index ith copy of original data, should be different across invocation because it is used for logging
   * @return a new instance of {@link SyncData}
   */
  SyncData copyMeta(int index);

  /**
   * Determine whether updated according to {@link java.util.Objects#deepEquals(Object, Object)}
   * @return any interested column is updated in this event
   * @see java.util.Objects#deepEquals(Object, Object)
   */
  boolean updated();

  /**
   * @param key column name
   * @return whether this key is {{@link #updated()}} in this event
   * @see java.util.Objects#deepEquals(Object, Object)
   */
  boolean updated(String key);

  Set<String> getUpdated();

  Object getBefore(String key);

  /**
   * @return null if no before info (MongoDB update event has no before info now)
   */
  HashMap<String, Object> getBefore();

  @Override
  String toString();
}
