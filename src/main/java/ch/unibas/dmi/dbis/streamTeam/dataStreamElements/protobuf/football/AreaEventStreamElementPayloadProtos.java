// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/football/areaEventStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football;

public final class AreaEventStreamElementPayloadProtos {
  private AreaEventStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AreaEventStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.football.AreaEventStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
     * </pre>
     *
     * <code>bool inArea = 1;</code>
     */
    boolean getInArea();

    /**
     * <pre>
     * Identifier of the area
     * </pre>
     *
     * <code>string areaId = 2;</code>
     */
    java.lang.String getAreaId();
    /**
     * <pre>
     * Identifier of the area
     * </pre>
     *
     * <code>string areaId = 2;</code>
     */
    com.google.protobuf.ByteString
        getAreaIdBytes();
  }
  /**
   * <pre>
   * Payload of an area event stream element
   * </pre>
   *
   * Protobuf type {@code streamTeam.football.AreaEventStreamElementPayload}
   */
  public  static final class AreaEventStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.football.AreaEventStreamElementPayload)
      AreaEventStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AreaEventStreamElementPayload.newBuilder() to construct.
    private AreaEventStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AreaEventStreamElementPayload() {
      areaId_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new AreaEventStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private AreaEventStreamElementPayload(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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

              inArea_ = input.readBool();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              areaId_ = s;
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.internal_static_streamTeam_football_AreaEventStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.Builder.class);
    }

    public static final int INAREA_FIELD_NUMBER = 1;
    private boolean inArea_;
    /**
     * <pre>
     * Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
     * </pre>
     *
     * <code>bool inArea = 1;</code>
     */
    public boolean getInArea() {
      return inArea_;
    }

    public static final int AREAID_FIELD_NUMBER = 2;
    private volatile java.lang.Object areaId_;
    /**
     * <pre>
     * Identifier of the area
     * </pre>
     *
     * <code>string areaId = 2;</code>
     */
    public java.lang.String getAreaId() {
      java.lang.Object ref = areaId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        areaId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Identifier of the area
     * </pre>
     *
     * <code>string areaId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getAreaIdBytes() {
      java.lang.Object ref = areaId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        areaId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

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
      if (inArea_ != false) {
        output.writeBool(1, inArea_);
      }
      if (!getAreaIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, areaId_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (inArea_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, inArea_);
      }
      if (!getAreaIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, areaId_);
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
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload) obj;

      if (getInArea()
          != other.getInArea()) return false;
      if (!getAreaId()
          .equals(other.getAreaId())) return false;
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
      hash = (37 * hash) + INAREA_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getInArea());
      hash = (37 * hash) + AREAID_FIELD_NUMBER;
      hash = (53 * hash) + getAreaId().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parseFrom(
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
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload prototype) {
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
     * Payload of an area event stream element
     * </pre>
     *
     * Protobuf type {@code streamTeam.football.AreaEventStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.football.AreaEventStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.internal_static_streamTeam_football_AreaEventStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.newBuilder()
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
        inArea_ = false;

        areaId_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload(this);
        result.inArea_ = inArea_;
        result.areaId_ = areaId_;
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
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.getDefaultInstance()) return this;
        if (other.getInArea() != false) {
          setInArea(other.getInArea());
        }
        if (!other.getAreaId().isEmpty()) {
          areaId_ = other.areaId_;
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
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private boolean inArea_ ;
      /**
       * <pre>
       * Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
       * </pre>
       *
       * <code>bool inArea = 1;</code>
       */
      public boolean getInArea() {
        return inArea_;
      }
      /**
       * <pre>
       * Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
       * </pre>
       *
       * <code>bool inArea = 1;</code>
       */
      public Builder setInArea(boolean value) {
        
        inArea_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
       * </pre>
       *
       * <code>bool inArea = 1;</code>
       */
      public Builder clearInArea() {
        
        inArea_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object areaId_ = "";
      /**
       * <pre>
       * Identifier of the area
       * </pre>
       *
       * <code>string areaId = 2;</code>
       */
      public java.lang.String getAreaId() {
        java.lang.Object ref = areaId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          areaId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Identifier of the area
       * </pre>
       *
       * <code>string areaId = 2;</code>
       */
      public com.google.protobuf.ByteString
          getAreaIdBytes() {
        java.lang.Object ref = areaId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          areaId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Identifier of the area
       * </pre>
       *
       * <code>string areaId = 2;</code>
       */
      public Builder setAreaId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        areaId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Identifier of the area
       * </pre>
       *
       * <code>string areaId = 2;</code>
       */
      public Builder clearAreaId() {
        
        areaId_ = getDefaultInstance().getAreaId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Identifier of the area
       * </pre>
       *
       * <code>string areaId = 2;</code>
       */
      public Builder setAreaIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        areaId_ = value;
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


      // @@protoc_insertion_point(builder_scope:streamTeam.football.AreaEventStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.football.AreaEventStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AreaEventStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<AreaEventStreamElementPayload>() {
      @java.lang.Override
      public AreaEventStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AreaEventStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AreaEventStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AreaEventStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_football_AreaEventStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nIsrc/main/protobuf/streamTeam/football/" +
      "areaEventStreamElementPayload.proto\022\023str" +
      "eamTeam.football\"?\n\035AreaEventStreamEleme" +
      "ntPayload\022\016\n\006inArea\030\001 \001(\010\022\016\n\006areaId\030\002 \001(" +
      "\tBi\nBch.unibas.dmi.dbis.streamTeam.dataS" +
      "treamElements.protobuf.footballB#AreaEve" +
      "ntStreamElementPayloadProtosb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_football_AreaEventStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_football_AreaEventStreamElementPayload_descriptor,
        new java.lang.String[] { "InArea", "AreaId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
