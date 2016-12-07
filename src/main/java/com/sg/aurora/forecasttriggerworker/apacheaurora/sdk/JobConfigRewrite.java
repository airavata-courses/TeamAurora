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
public class JobConfigRewrite implements org.apache.thrift.TBase<JobConfigRewrite, JobConfigRewrite._Fields>, java.io.Serializable, Cloneable, Comparable<JobConfigRewrite> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("JobConfigRewrite");

  private static final org.apache.thrift.protocol.TField OLD_JOB_FIELD_DESC = new org.apache.thrift.protocol.TField("oldJob", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField REWRITTEN_JOB_FIELD_DESC = new org.apache.thrift.protocol.TField("rewrittenJob", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new JobConfigRewriteStandardSchemeFactory());
    schemes.put(TupleScheme.class, new JobConfigRewriteTupleSchemeFactory());
  }

  /**
   * The original job configuration.
   */
  public JobConfiguration oldJob; // required
  /**
   * The rewritten job configuration.
   */
  public JobConfiguration rewrittenJob; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * The original job configuration.
     */
    OLD_JOB((short)1, "oldJob"),
    /**
     * The rewritten job configuration.
     */
    REWRITTEN_JOB((short)2, "rewrittenJob");

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
        case 1: // OLD_JOB
          return OLD_JOB;
        case 2: // REWRITTEN_JOB
          return REWRITTEN_JOB;
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
    tmpMap.put(_Fields.OLD_JOB, new org.apache.thrift.meta_data.FieldMetaData("oldJob", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, JobConfiguration.class)));
    tmpMap.put(_Fields.REWRITTEN_JOB, new org.apache.thrift.meta_data.FieldMetaData("rewrittenJob", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, JobConfiguration.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(JobConfigRewrite.class, metaDataMap);
  }

  public JobConfigRewrite() {
  }

  public JobConfigRewrite(
    JobConfiguration oldJob,
    JobConfiguration rewrittenJob)
  {
    this();
    this.oldJob = oldJob;
    this.rewrittenJob = rewrittenJob;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public JobConfigRewrite(JobConfigRewrite other) {
    if (other.isSetOldJob()) {
      this.oldJob = new JobConfiguration(other.oldJob);
    }
    if (other.isSetRewrittenJob()) {
      this.rewrittenJob = new JobConfiguration(other.rewrittenJob);
    }
  }

  public JobConfigRewrite deepCopy() {
    return new JobConfigRewrite(this);
  }

  @Override
  public void clear() {
    this.oldJob = null;
    this.rewrittenJob = null;
  }

  /**
   * The original job configuration.
   */
  public JobConfiguration getOldJob() {
    return this.oldJob;
  }

  /**
   * The original job configuration.
   */
  public JobConfigRewrite setOldJob(JobConfiguration oldJob) {
    this.oldJob = oldJob;
    return this;
  }

  public void unsetOldJob() {
    this.oldJob = null;
  }

  /** Returns true if field oldJob is set (has been assigned a value) and false otherwise */
  public boolean isSetOldJob() {
    return this.oldJob != null;
  }

  public void setOldJobIsSet(boolean value) {
    if (!value) {
      this.oldJob = null;
    }
  }

  /**
   * The rewritten job configuration.
   */
  public JobConfiguration getRewrittenJob() {
    return this.rewrittenJob;
  }

  /**
   * The rewritten job configuration.
   */
  public JobConfigRewrite setRewrittenJob(JobConfiguration rewrittenJob) {
    this.rewrittenJob = rewrittenJob;
    return this;
  }

  public void unsetRewrittenJob() {
    this.rewrittenJob = null;
  }

  /** Returns true if field rewrittenJob is set (has been assigned a value) and false otherwise */
  public boolean isSetRewrittenJob() {
    return this.rewrittenJob != null;
  }

  public void setRewrittenJobIsSet(boolean value) {
    if (!value) {
      this.rewrittenJob = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case OLD_JOB:
      if (value == null) {
        unsetOldJob();
      } else {
        setOldJob((JobConfiguration)value);
      }
      break;

    case REWRITTEN_JOB:
      if (value == null) {
        unsetRewrittenJob();
      } else {
        setRewrittenJob((JobConfiguration)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case OLD_JOB:
      return getOldJob();

    case REWRITTEN_JOB:
      return getRewrittenJob();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case OLD_JOB:
      return isSetOldJob();
    case REWRITTEN_JOB:
      return isSetRewrittenJob();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof JobConfigRewrite)
      return this.equals((JobConfigRewrite)that);
    return false;
  }

  public boolean equals(JobConfigRewrite that) {
    if (that == null)
      return false;

    boolean this_present_oldJob = true && this.isSetOldJob();
    boolean that_present_oldJob = true && that.isSetOldJob();
    if (this_present_oldJob || that_present_oldJob) {
      if (!(this_present_oldJob && that_present_oldJob))
        return false;
      if (!this.oldJob.equals(that.oldJob))
        return false;
    }

    boolean this_present_rewrittenJob = true && this.isSetRewrittenJob();
    boolean that_present_rewrittenJob = true && that.isSetRewrittenJob();
    if (this_present_rewrittenJob || that_present_rewrittenJob) {
      if (!(this_present_rewrittenJob && that_present_rewrittenJob))
        return false;
      if (!this.rewrittenJob.equals(that.rewrittenJob))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_oldJob = true && (isSetOldJob());
    list.add(present_oldJob);
    if (present_oldJob)
      list.add(oldJob);

    boolean present_rewrittenJob = true && (isSetRewrittenJob());
    list.add(present_rewrittenJob);
    if (present_rewrittenJob)
      list.add(rewrittenJob);

    return list.hashCode();
  }

  @Override
  public int compareTo(JobConfigRewrite other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetOldJob()).compareTo(other.isSetOldJob());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOldJob()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.oldJob, other.oldJob);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRewrittenJob()).compareTo(other.isSetRewrittenJob());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRewrittenJob()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rewrittenJob, other.rewrittenJob);
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
    StringBuilder sb = new StringBuilder("JobConfigRewrite(");
    boolean first = true;

    sb.append("oldJob:");
    if (this.oldJob == null) {
      sb.append("null");
    } else {
      sb.append(this.oldJob);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("rewrittenJob:");
    if (this.rewrittenJob == null) {
      sb.append("null");
    } else {
      sb.append(this.rewrittenJob);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (oldJob != null) {
      oldJob.validate();
    }
    if (rewrittenJob != null) {
      rewrittenJob.validate();
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

  private static class JobConfigRewriteStandardSchemeFactory implements SchemeFactory {
    public JobConfigRewriteStandardScheme getScheme() {
      return new JobConfigRewriteStandardScheme();
    }
  }

  private static class JobConfigRewriteStandardScheme extends StandardScheme<JobConfigRewrite> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, JobConfigRewrite struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // OLD_JOB
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.oldJob = new JobConfiguration();
              struct.oldJob.read(iprot);
              struct.setOldJobIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REWRITTEN_JOB
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.rewrittenJob = new JobConfiguration();
              struct.rewrittenJob.read(iprot);
              struct.setRewrittenJobIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, JobConfigRewrite struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.oldJob != null) {
        oprot.writeFieldBegin(OLD_JOB_FIELD_DESC);
        struct.oldJob.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.rewrittenJob != null) {
        oprot.writeFieldBegin(REWRITTEN_JOB_FIELD_DESC);
        struct.rewrittenJob.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobConfigRewriteTupleSchemeFactory implements SchemeFactory {
    public JobConfigRewriteTupleScheme getScheme() {
      return new JobConfigRewriteTupleScheme();
    }
  }

  private static class JobConfigRewriteTupleScheme extends TupleScheme<JobConfigRewrite> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, JobConfigRewrite struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetOldJob()) {
        optionals.set(0);
      }
      if (struct.isSetRewrittenJob()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetOldJob()) {
        struct.oldJob.write(oprot);
      }
      if (struct.isSetRewrittenJob()) {
        struct.rewrittenJob.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, JobConfigRewrite struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.oldJob = new JobConfiguration();
        struct.oldJob.read(iprot);
        struct.setOldJobIsSet(true);
      }
      if (incoming.get(1)) {
        struct.rewrittenJob = new JobConfiguration();
        struct.rewrittenJob.read(iprot);
        struct.setRewrittenJobIsSet(true);
      }
    }
  }

}

