// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/dummyStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf;

public final class DummyStreamElementPayloadProtos {
  private DummyStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface DummyStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.DummyStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Dummy long value
     * </pre>
     *
     * <code>int64 longValue = 1;</code>
     */
    long getLongValue();

    /**
     * <pre>
     * Dummy bool value
     * </pre>
     *
     * <code>bool boolValue = 2;</code>
     */
    boolean getBoolValue();

    /**
     * <pre>
     * Dummy string value
     * </pre>
     *
     * <code>string stringValue = 3;</code>
     */
    java.lang.String getStringValue();
    /**
     * <pre>
     * Dummy string value
     * </pre>
     *
     * <code>string stringValue = 3;</code>
     */
    com.google.protobuf.ByteString
        getStringValueBytes();

    /**
     * <pre>
     * Dummy double value
     * </pre>
     *
     * <code>double doubleValue = 4;</code>
     */
    double getDoubleValue();

    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    java.util.List<java.lang.Long> getRepeatedValueList();
    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    int getRepeatedValueCount();
    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    long getRepeatedValue(int index);
  }
  /**
   * <pre>
   * Payload of a dummy stream element
   * </pre>
   *
   * Protobuf type {@code streamTeam.DummyStreamElementPayload}
   */
  public  static final class DummyStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.DummyStreamElementPayload)
      DummyStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use DummyStreamElementPayload.newBuilder() to construct.
    private DummyStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DummyStreamElementPayload() {
      stringValue_ = "";
      repeatedValue_ = emptyLongList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new DummyStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private DummyStreamElementPayload(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              longValue_ = input.readInt64();
              break;
            }
            case 16: {

              boolValue_ = input.readBool();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              stringValue_ = s;
              break;
            }
            case 33: {

              doubleValue_ = input.readDouble();
              break;
            }
            case 40: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                repeatedValue_ = newLongList();
                mutable_bitField0_ |= 0x00000001;
              }
              repeatedValue_.addLong(input.readInt64());
              break;
            }
            case 42: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
                repeatedValue_ = newLongList();
                mutable_bitField0_ |= 0x00000001;
              }
              while (input.getBytesUntilLimit() > 0) {
                repeatedValue_.addLong(input.readInt64());
              }
              input.popLimit(limit);
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          repeatedValue_.makeImmutable(); // C
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.internal_static_streamTeam_DummyStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.internal_static_streamTeam_DummyStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.Builder.class);
    }

    public static final int LONGVALUE_FIELD_NUMBER = 1;
    private long longValue_;
    /**
     * <pre>
     * Dummy long value
     * </pre>
     *
     * <code>int64 longValue = 1;</code>
     */
    public long getLongValue() {
      return longValue_;
    }

    public static final int BOOLVALUE_FIELD_NUMBER = 2;
    private boolean boolValue_;
    /**
     * <pre>
     * Dummy bool value
     * </pre>
     *
     * <code>bool boolValue = 2;</code>
     */
    public boolean getBoolValue() {
      return boolValue_;
    }

    public static final int STRINGVALUE_FIELD_NUMBER = 3;
    private volatile java.lang.Object stringValue_;
    /**
     * <pre>
     * Dummy string value
     * </pre>
     *
     * <code>string stringValue = 3;</code>
     */
    public java.lang.String getStringValue() {
      java.lang.Object ref = stringValue_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stringValue_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Dummy string value
     * </pre>
     *
     * <code>string stringValue = 3;</code>
     */
    public com.google.protobuf.ByteString
        getStringValueBytes() {
      java.lang.Object ref = stringValue_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stringValue_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DOUBLEVALUE_FIELD_NUMBER = 4;
    private double doubleValue_;
    /**
     * <pre>
     * Dummy double value
     * </pre>
     *
     * <code>double doubleValue = 4;</code>
     */
    public double getDoubleValue() {
      return doubleValue_;
    }

    public static final int REPEATEDVALUE_FIELD_NUMBER = 5;
    private com.google.protobuf.Internal.LongList repeatedValue_;
    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    public java.util.List<java.lang.Long>
        getRepeatedValueList() {
      return repeatedValue_;
    }
    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    public int getRepeatedValueCount() {
      return repeatedValue_.size();
    }
    /**
     * <pre>
     * Dummy repeated value
     * </pre>
     *
     * <code>repeated int64 repeatedValue = 5;</code>
     */
    public long getRepeatedValue(int index) {
      return repeatedValue_.getLong(index);
    }
    private int repeatedValueMemoizedSerializedSize = -1;

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (longValue_ != 0L) {
        output.writeInt64(1, longValue_);
      }
      if (boolValue_ != false) {
        output.writeBool(2, boolValue_);
      }
      if (!getStringValueBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, stringValue_);
      }
      if (doubleValue_ != 0D) {
        output.writeDouble(4, doubleValue_);
      }
      if (getRepeatedValueList().size() > 0) {
        output.writeUInt32NoTag(42);
        output.writeUInt32NoTag(repeatedValueMemoizedSerializedSize);
      }
      for (int i = 0; i < repeatedValue_.size(); i++) {
        output.writeInt64NoTag(repeatedValue_.getLong(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (longValue_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, longValue_);
      }
      if (boolValue_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, boolValue_);
      }
      if (!getStringValueBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, stringValue_);
      }
      if (doubleValue_ != 0D) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(4, doubleValue_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < repeatedValue_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt64SizeNoTag(repeatedValue_.getLong(i));
        }
        size += dataSize;
        if (!getRepeatedValueList().isEmpty()) {
          size += 1;
          size += com.google.protobuf.CodedOutputStream
              .computeInt32SizeNoTag(dataSize);
        }
        repeatedValueMemoizedSerializedSize = dataSize;
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload) obj;

      if (getLongValue()
          != other.getLongValue()) return false;
      if (getBoolValue()
          != other.getBoolValue()) return false;
      if (!getStringValue()
          .equals(other.getStringValue())) return false;
      if (java.lang.Double.doubleToLongBits(getDoubleValue())
          != java.lang.Double.doubleToLongBits(
              other.getDoubleValue())) return false;
      if (!getRepeatedValueList()
          .equals(other.getRepeatedValueList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + LONGVALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getLongValue());
      hash = (37 * hash) + BOOLVALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getBoolValue());
      hash = (37 * hash) + STRINGVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getStringValue().hashCode();
      hash = (37 * hash) + DOUBLEVALUE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getDoubleValue()));
      if (getRepeatedValueCount() > 0) {
        hash = (37 * hash) + REPEATEDVALUE_FIELD_NUMBER;
        hash = (53 * hash) + getRepeatedValueList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * Payload of a dummy stream element
     * </pre>
     *
     * Protobuf type {@code streamTeam.DummyStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.DummyStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.internal_static_streamTeam_DummyStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.internal_static_streamTeam_DummyStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        longValue_ = 0L;

        boolValue_ = false;

        stringValue_ = "";

        doubleValue_ = 0D;

        repeatedValue_ = emptyLongList();
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.internal_static_streamTeam_DummyStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload(this);
        int from_bitField0_ = bitField0_;
        result.longValue_ = longValue_;
        result.boolValue_ = boolValue_;
        result.stringValue_ = stringValue_;
        result.doubleValue_ = doubleValue_;
        if (((bitField0_ & 0x00000001) != 0)) {
          repeatedValue_.makeImmutable();
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.repeatedValue_ = repeatedValue_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload.getDefaultInstance()) return this;
        if (other.getLongValue() != 0L) {
          setLongValue(other.getLongValue());
        }
        if (other.getBoolValue() != false) {
          setBoolValue(other.getBoolValue());
        }
        if (!other.getStringValue().isEmpty()) {
          stringValue_ = other.stringValue_;
          onChanged();
        }
        if (other.getDoubleValue() != 0D) {
          setDoubleValue(other.getDoubleValue());
        }
        if (!other.repeatedValue_.isEmpty()) {
          if (repeatedValue_.isEmpty()) {
            repeatedValue_ = other.repeatedValue_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureRepeatedValueIsMutable();
            repeatedValue_.addAll(other.repeatedValue_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long longValue_ ;
      /**
       * <pre>
       * Dummy long value
       * </pre>
       *
       * <code>int64 longValue = 1;</code>
       */
      public long getLongValue() {
        return longValue_;
      }
      /**
       * <pre>
       * Dummy long value
       * </pre>
       *
       * <code>int64 longValue = 1;</code>
       */
      public Builder setLongValue(long value) {
        
        longValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy long value
       * </pre>
       *
       * <code>int64 longValue = 1;</code>
       */
      public Builder clearLongValue() {
        
        longValue_ = 0L;
        onChanged();
        return this;
      }

      private boolean boolValue_ ;
      /**
       * <pre>
       * Dummy bool value
       * </pre>
       *
       * <code>bool boolValue = 2;</code>
       */
      public boolean getBoolValue() {
        return boolValue_;
      }
      /**
       * <pre>
       * Dummy bool value
       * </pre>
       *
       * <code>bool boolValue = 2;</code>
       */
      public Builder setBoolValue(boolean value) {
        
        boolValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy bool value
       * </pre>
       *
       * <code>bool boolValue = 2;</code>
       */
      public Builder clearBoolValue() {
        
        boolValue_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object stringValue_ = "";
      /**
       * <pre>
       * Dummy string value
       * </pre>
       *
       * <code>string stringValue = 3;</code>
       */
      public java.lang.String getStringValue() {
        java.lang.Object ref = stringValue_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          stringValue_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Dummy string value
       * </pre>
       *
       * <code>string stringValue = 3;</code>
       */
      public com.google.protobuf.ByteString
          getStringValueBytes() {
        java.lang.Object ref = stringValue_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          stringValue_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Dummy string value
       * </pre>
       *
       * <code>string stringValue = 3;</code>
       */
      public Builder setStringValue(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        stringValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy string value
       * </pre>
       *
       * <code>string stringValue = 3;</code>
       */
      public Builder clearStringValue() {
        
        stringValue_ = getDefaultInstance().getStringValue();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy string value
       * </pre>
       *
       * <code>string stringValue = 3;</code>
       */
      public Builder setStringValueBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        stringValue_ = value;
        onChanged();
        return this;
      }

      private double doubleValue_ ;
      /**
       * <pre>
       * Dummy double value
       * </pre>
       *
       * <code>double doubleValue = 4;</code>
       */
      public double getDoubleValue() {
        return doubleValue_;
      }
      /**
       * <pre>
       * Dummy double value
       * </pre>
       *
       * <code>double doubleValue = 4;</code>
       */
      public Builder setDoubleValue(double value) {
        
        doubleValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy double value
       * </pre>
       *
       * <code>double doubleValue = 4;</code>
       */
      public Builder clearDoubleValue() {
        
        doubleValue_ = 0D;
        onChanged();
        return this;
      }

      private com.google.protobuf.Internal.LongList repeatedValue_ = emptyLongList();
      private void ensureRepeatedValueIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          repeatedValue_ = mutableCopy(repeatedValue_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public java.util.List<java.lang.Long>
          getRepeatedValueList() {
        return ((bitField0_ & 0x00000001) != 0) ?
                 java.util.Collections.unmodifiableList(repeatedValue_) : repeatedValue_;
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public int getRepeatedValueCount() {
        return repeatedValue_.size();
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public long getRepeatedValue(int index) {
        return repeatedValue_.getLong(index);
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public Builder setRepeatedValue(
          int index, long value) {
        ensureRepeatedValueIsMutable();
        repeatedValue_.setLong(index, value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public Builder addRepeatedValue(long value) {
        ensureRepeatedValueIsMutable();
        repeatedValue_.addLong(value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public Builder addAllRepeatedValue(
          java.lang.Iterable<? extends java.lang.Long> values) {
        ensureRepeatedValueIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, repeatedValue_);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Dummy repeated value
       * </pre>
       *
       * <code>repeated int64 repeatedValue = 5;</code>
       */
      public Builder clearRepeatedValue() {
        repeatedValue_ = emptyLongList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:streamTeam.DummyStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.DummyStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DummyStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<DummyStreamElementPayload>() {
      @java.lang.Override
      public DummyStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DummyStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<DummyStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DummyStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos.DummyStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_DummyStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_DummyStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n<src/main/protobuf/streamTeam/dummyStre" +
      "amElementPayload.proto\022\nstreamTeam\"\202\001\n\031D" +
      "ummyStreamElementPayload\022\021\n\tlongValue\030\001 " +
      "\001(\003\022\021\n\tboolValue\030\002 \001(\010\022\023\n\013stringValue\030\003 " +
      "\001(\t\022\023\n\013doubleValue\030\004 \001(\001\022\025\n\rrepeatedValu" +
      "e\030\005 \003(\003B\\\n9ch.unibas.dmi.dbis.streamTeam" +
      ".dataStreamElements.protobufB\037DummyStrea" +
      "mElementPayloadProtosb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_DummyStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_DummyStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_DummyStreamElementPayload_descriptor,
        new java.lang.String[] { "LongValue", "BoolValue", "StringValue", "DoubleValue", "RepeatedValue", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
