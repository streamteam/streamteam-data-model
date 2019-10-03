// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/football/passSequenceEventStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football;

public final class PassSequenceEventStreamElementPayloadProtos {
  private PassSequenceEventStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PassSequenceEventStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.football.PassSequenceEventStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Number of passes
     * </pre>
     *
     * <code>int64 numPasses = 1;</code>
     */
    long getNumPasses();
  }
  /**
   * <pre>
   * Payload of a passSequenceEvent stream element
   * </pre>
   *
   * Protobuf type {@code streamTeam.football.PassSequenceEventStreamElementPayload}
   */
  public  static final class PassSequenceEventStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.football.PassSequenceEventStreamElementPayload)
      PassSequenceEventStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use PassSequenceEventStreamElementPayload.newBuilder() to construct.
    private PassSequenceEventStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PassSequenceEventStreamElementPayload() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new PassSequenceEventStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PassSequenceEventStreamElementPayload(
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

              numPasses_ = input.readInt64();
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
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.Builder.class);
    }

    public static final int NUMPASSES_FIELD_NUMBER = 1;
    private long numPasses_;
    /**
     * <pre>
     * Number of passes
     * </pre>
     *
     * <code>int64 numPasses = 1;</code>
     */
    public long getNumPasses() {
      return numPasses_;
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
      if (numPasses_ != 0L) {
        output.writeInt64(1, numPasses_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (numPasses_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, numPasses_);
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
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload) obj;

      if (getNumPasses()
          != other.getNumPasses()) return false;
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
      hash = (37 * hash) + NUMPASSES_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getNumPasses());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parseFrom(
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
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload prototype) {
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
     * Payload of a passSequenceEvent stream element
     * </pre>
     *
     * Protobuf type {@code streamTeam.football.PassSequenceEventStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.football.PassSequenceEventStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.newBuilder()
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
        numPasses_ = 0L;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload(this);
        result.numPasses_ = numPasses_;
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
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.getDefaultInstance()) return this;
        if (other.getNumPasses() != 0L) {
          setNumPasses(other.getNumPasses());
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
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long numPasses_ ;
      /**
       * <pre>
       * Number of passes
       * </pre>
       *
       * <code>int64 numPasses = 1;</code>
       */
      public long getNumPasses() {
        return numPasses_;
      }
      /**
       * <pre>
       * Number of passes
       * </pre>
       *
       * <code>int64 numPasses = 1;</code>
       */
      public Builder setNumPasses(long value) {
        
        numPasses_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Number of passes
       * </pre>
       *
       * <code>int64 numPasses = 1;</code>
       */
      public Builder clearNumPasses() {
        
        numPasses_ = 0L;
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


      // @@protoc_insertion_point(builder_scope:streamTeam.football.PassSequenceEventStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.football.PassSequenceEventStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PassSequenceEventStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<PassSequenceEventStreamElementPayload>() {
      @java.lang.Override
      public PassSequenceEventStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PassSequenceEventStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PassSequenceEventStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PassSequenceEventStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nQsrc/main/protobuf/streamTeam/football/" +
      "passSequenceEventStreamElementPayload.pr" +
      "oto\022\023streamTeam.football\":\n%PassSequence" +
      "EventStreamElementPayload\022\021\n\tnumPasses\030\001" +
      " \001(\003Bq\nBch.unibas.dmi.dbis.streamTeam.da" +
      "taStreamElements.protobuf.footballB+Pass" +
      "SequenceEventStreamElementPayloadProtosb" +
      "\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_football_PassSequenceEventStreamElementPayload_descriptor,
        new java.lang.String[] { "NumPasses", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
