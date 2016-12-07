/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sg.aurora.forecasttriggerworker.apacheaurora.sdk;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-12-03")
public class JobUpdateDetails implements org.apache.thrift.TBase<JobUpdateDetails, JobUpdateDetails._Fields>, java.io.Serializable, Cloneable, Comparable<JobUpdateDetails> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("JobUpdateDetails");

  private static final org.apache.thrift.protocol.TField UPDATE_FIELD_DESC = new org.apache.thrift.protocol.TField("update", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField UPDATE_EVENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("updateEvents", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField INSTANCE_EVENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("instanceEvents", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new JobUpdateDetailsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new JobUpdateDetailsTupleSchemeFactory());
  }

  /**
   * Update definition.
   */
  public JobUpdate update; // required
  /**
   * History for this update.
   */
  public List<JobUpdateEvent> updateEvents; // required
  /**
   * History for the individual instances updated.
   */
  public List<JobInstanceUpdateEvent> instanceEvents; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * Update definition.
     */
    UPDATE((short)1, "update"),
    /**
     * History for this update.
     */
    UPDATE_EVENTS((short)2, "updateEvents"),
    /**
     * History for the individual instances updated.
     */
    INSTANCE_EVENTS((short)3, "instanceEvents");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // UPDATE
          return UPDATE;
        case 2: // UPDATE_EVENTS
          return UPDATE_EVENTS;
        case 3: // INSTANCE_EVENTS
          return INSTANCE_EVENTS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.UPDATE, new org.apache.thrift.meta_data.FieldMetaData("update", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, JobUpdate.class)));
    tmpMap.put(_Fields.UPDATE_EVENTS, new org.apache.thrift.meta_data.FieldMetaData("updateEvents", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, JobUpdateEvent.class))));
    tmpMap.put(_Fields.INSTANCE_EVENTS, new org.apache.thrift.meta_data.FieldMetaData("instanceEvents", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, JobInstanceUpdateEvent.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(JobUpdateDetails.class, metaDataMap);
  }

  public JobUpdateDetails() {
  }

  public JobUpdateDetails(
    JobUpdate update,
    List<JobUpdateEvent> updateEvents,
    List<JobInstanceUpdateEvent> instanceEvents)
  {
    this();
    this.update = update;
    this.updateEvents = updateEvents;
    this.instanceEvents = instanceEvents;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public JobUpdateDetails(JobUpdateDetails other) {
    if (other.isSetUpdate()) {
      this.update = new JobUpdate(other.update);
    }
    if (other.isSetUpdateEvents()) {
      List<JobUpdateEvent> __this__updateEvents = new ArrayList<JobUpdateEvent>(other.updateEvents.size());
      for (JobUpdateEvent other_element : other.updateEvents) {
        __this__updateEvents.add(new JobUpdateEvent(other_element));
      }
      this.updateEvents = __this__updateEvents;
    }
    if (other.isSetInstanceEvents()) {
      List<JobInstanceUpdateEvent> __this__instanceEvents = new ArrayList<JobInstanceUpdateEvent>(other.instanceEvents.size());
      for (JobInstanceUpdateEvent other_element : other.instanceEvents) {
        __this__instanceEvents.add(new JobInstanceUpdateEvent(other_element));
      }
      this.instanceEvents = __this__instanceEvents;
    }
  }

  public JobUpdateDetails deepCopy() {
    return new JobUpdateDetails(this);
  }

  @Override
  public void clear() {
    this.update = null;
    this.updateEvents = null;
    this.instanceEvents = null;
  }

  /**
   * Update definition.
   */
  public JobUpdate getUpdate() {
    return this.update;
  }

  /**
   * Update definition.
   */
  public JobUpdateDetails setUpdate(JobUpdate update) {
    this.update = update;
    return this;
  }

  public void unsetUpdate() {
    this.update = null;
  }

  /** Returns true if field update is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdate() {
    return this.update != null;
  }

  public void setUpdateIsSet(boolean value) {
    if (!value) {
      this.update = null;
    }
  }

  public int getUpdateEventsSize() {
    return (this.updateEvents == null) ? 0 : this.updateEvents.size();
  }

  public java.util.Iterator<JobUpdateEvent> getUpdateEventsIterator() {
    return (this.updateEvents == null) ? null : this.updateEvents.iterator();
  }

  public void addToUpdateEvents(JobUpdateEvent elem) {
    if (this.updateEvents == null) {
      this.updateEvents = new ArrayList<JobUpdateEvent>();
    }
    this.updateEvents.add(elem);
  }

  /**
   * History for this update.
   */
  public List<JobUpdateEvent> getUpdateEvents() {
    return this.updateEvents;
  }

  /**
   * History for this update.
   */
  public JobUpdateDetails setUpdateEvents(List<JobUpdateEvent> updateEvents) {
    this.updateEvents = updateEvents;
    return this;
  }

  public void unsetUpdateEvents() {
    this.updateEvents = null;
  }

  /** Returns true if field updateEvents is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdateEvents() {
    return this.updateEvents != null;
  }

  public void setUpdateEventsIsSet(boolean value) {
    if (!value) {
      this.updateEvents = null;
    }
  }

  public int getInstanceEventsSize() {
    return (this.instanceEvents == null) ? 0 : this.instanceEvents.size();
  }

  public java.util.Iterator<JobInstanceUpdateEvent> getInstanceEventsIterator() {
    return (this.instanceEvents == null) ? null : this.instanceEvents.iterator();
  }

  public void addToInstanceEvents(JobInstanceUpdateEvent elem) {
    if (this.instanceEvents == null) {
      this.instanceEvents = new ArrayList<JobInstanceUpdateEvent>();
    }
    this.instanceEvents.add(elem);
  }

  /**
   * History for the individual instances updated.
   */
  public List<JobInstanceUpdateEvent> getInstanceEvents() {
    return this.instanceEvents;
  }

  /**
   * History for the individual instances updated.
   */
  public JobUpdateDetails setInstanceEvents(List<JobInstanceUpdateEvent> instanceEvents) {
    this.instanceEvents = instanceEvents;
    return this;
  }

  public void unsetInstanceEvents() {
    this.instanceEvents = null;
  }

  /** Returns true if field instanceEvents is set (has been assigned a value) and false otherwise */
  public boolean isSetInstanceEvents() {
    return this.instanceEvents != null;
  }

  public void setInstanceEventsIsSet(boolean value) {
    if (!value) {
      this.instanceEvents = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case UPDATE:
      if (value == null) {
        unsetUpdate();
      } else {
        setUpdate((JobUpdate)value);
      }
      break;

    case UPDATE_EVENTS:
      if (value == null) {
        unsetUpdateEvents();
      } else {
        setUpdateEvents((List<JobUpdateEvent>)value);
      }
      break;

    case INSTANCE_EVENTS:
      if (value == null) {
        unsetInstanceEvents();
      } else {
        setInstanceEvents((List<JobInstanceUpdateEvent>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case UPDATE:
      return getUpdate();

    case UPDATE_EVENTS:
      return getUpdateEvents();

    case INSTANCE_EVENTS:
      return getInstanceEvents();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case UPDATE:
      return isSetUpdate();
    case UPDATE_EVENTS:
      return isSetUpdateEvents();
    case INSTANCE_EVENTS:
      return isSetInstanceEvents();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof JobUpdateDetails)
      return this.equals((JobUpdateDetails)that);
    return false;
  }

  public boolean equals(JobUpdateDetails that) {
    if (that == null)
      return false;

    boolean this_present_update = true && this.isSetUpdate();
    boolean that_present_update = true && that.isSetUpdate();
    if (this_present_update || that_present_update) {
      if (!(this_present_update && that_present_update))
        return false;
      if (!this.update.equals(that.update))
        return false;
    }

    boolean this_present_updateEvents = true && this.isSetUpdateEvents();
    boolean that_present_updateEvents = true && that.isSetUpdateEvents();
    if (this_present_updateEvents || that_present_updateEvents) {
      if (!(this_present_updateEvents && that_present_updateEvents))
        return false;
      if (!this.updateEvents.equals(that.updateEvents))
        return false;
    }

    boolean this_present_instanceEvents = true && this.isSetInstanceEvents();
    boolean that_present_instanceEvents = true && that.isSetInstanceEvents();
    if (this_present_instanceEvents || that_present_instanceEvents) {
      if (!(this_present_instanceEvents && that_present_instanceEvents))
        return false;
      if (!this.instanceEvents.equals(that.instanceEvents))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_update = true && (isSetUpdate());
    list.add(present_update);
    if (present_update)
      list.add(update);

    boolean present_updateEvents = true && (isSetUpdateEvents());
    list.add(present_updateEvents);
    if (present_updateEvents)
      list.add(updateEvents);

    boolean present_instanceEvents = true && (isSetInstanceEvents());
    list.add(present_instanceEvents);
    if (present_instanceEvents)
      list.add(instanceEvents);

    return list.hashCode();
  }

  @Override
  public int compareTo(JobUpdateDetails other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUpdate()).compareTo(other.isSetUpdate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.update, other.update);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUpdateEvents()).compareTo(other.isSetUpdateEvents());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdateEvents()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.updateEvents, other.updateEvents);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInstanceEvents()).compareTo(other.isSetInstanceEvents());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInstanceEvents()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.instanceEvents, other.instanceEvents);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("JobUpdateDetails(");
    boolean first = true;

    sb.append("update:");
    if (this.update == null) {
      sb.append("null");
    } else {
      sb.append(this.update);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("updateEvents:");
    if (this.updateEvents == null) {
      sb.append("null");
    } else {
      sb.append(this.updateEvents);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("instanceEvents:");
    if (this.instanceEvents == null) {
      sb.append("null");
    } else {
      sb.append(this.instanceEvents);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (update != null) {
      update.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class JobUpdateDetailsStandardSchemeFactory implements SchemeFactory {
    public JobUpdateDetailsStandardScheme getScheme() {
      return new JobUpdateDetailsStandardScheme();
    }
  }

  private static class JobUpdateDetailsStandardScheme extends StandardScheme<JobUpdateDetails> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, JobUpdateDetails struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // UPDATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.update = new JobUpdate();
              struct.update.read(iprot);
              struct.setUpdateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // UPDATE_EVENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list230 = iprot.readListBegin();
                struct.updateEvents = new ArrayList<JobUpdateEvent>(_list230.size);
                JobUpdateEvent _elem231;
                for (int _i232 = 0; _i232 < _list230.size; ++_i232)
                {
                  _elem231 = new JobUpdateEvent();
                  _elem231.read(iprot);
                  struct.updateEvents.add(_elem231);
                }
                iprot.readListEnd();
              }
              struct.setUpdateEventsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // INSTANCE_EVENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list233 = iprot.readListBegin();
                struct.instanceEvents = new ArrayList<JobInstanceUpdateEvent>(_list233.size);
                JobInstanceUpdateEvent _elem234;
                for (int _i235 = 0; _i235 < _list233.size; ++_i235)
                {
                  _elem234 = new JobInstanceUpdateEvent();
                  _elem234.read(iprot);
                  struct.instanceEvents.add(_elem234);
                }
                iprot.readListEnd();
              }
              struct.setInstanceEventsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, JobUpdateDetails struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.update != null) {
        oprot.writeFieldBegin(UPDATE_FIELD_DESC);
        struct.update.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.updateEvents != null) {
        oprot.writeFieldBegin(UPDATE_EVENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.updateEvents.size()));
          for (JobUpdateEvent _iter236 : struct.updateEvents)
          {
            _iter236.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.instanceEvents != null) {
        oprot.writeFieldBegin(INSTANCE_EVENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.instanceEvents.size()));
          for (JobInstanceUpdateEvent _iter237 : struct.instanceEvents)
          {
            _iter237.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobUpdateDetailsTupleSchemeFactory implements SchemeFactory {
    public JobUpdateDetailsTupleScheme getScheme() {
      return new JobUpdateDetailsTupleScheme();
    }
  }

  private static class JobUpdateDetailsTupleScheme extends TupleScheme<JobUpdateDetails> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, JobUpdateDetails struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetUpdate()) {
        optionals.set(0);
      }
      if (struct.isSetUpdateEvents()) {
        optionals.set(1);
      }
      if (struct.isSetInstanceEvents()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetUpdate()) {
        struct.update.write(oprot);
      }
      if (struct.isSetUpdateEvents()) {
        {
          oprot.writeI32(struct.updateEvents.size());
          for (JobUpdateEvent _iter238 : struct.updateEvents)
          {
            _iter238.write(oprot);
          }
        }
      }
      if (struct.isSetInstanceEvents()) {
        {
          oprot.writeI32(struct.instanceEvents.size());
          for (JobInstanceUpdateEvent _iter239 : struct.instanceEvents)
          {
            _iter239.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, JobUpdateDetails struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.update = new JobUpdate();
        struct.update.read(iprot);
        struct.setUpdateIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list240 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.updateEvents = new ArrayList<JobUpdateEvent>(_list240.size);
          JobUpdateEvent _elem241;
          for (int _i242 = 0; _i242 < _list240.size; ++_i242)
          {
            _elem241 = new JobUpdateEvent();
            _elem241.read(iprot);
            struct.updateEvents.add(_elem241);
          }
        }
        struct.setUpdateEventsIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list243 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.instanceEvents = new ArrayList<JobInstanceUpdateEvent>(_list243.size);
          JobInstanceUpdateEvent _elem244;
          for (int _i245 = 0; _i245 < _list243.size; ++_i245)
          {
            _elem244 = new JobInstanceUpdateEvent();
            _elem244.read(iprot);
            struct.instanceEvents.add(_elem244);
          }
        }
        struct.setInstanceEventsIsSet(true);
      }
    }
  }

}

