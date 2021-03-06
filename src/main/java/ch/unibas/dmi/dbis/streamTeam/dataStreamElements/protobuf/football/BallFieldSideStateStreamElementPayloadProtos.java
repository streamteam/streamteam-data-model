// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/football/ballFieldSideStateStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football;

public final class BallFieldSideStateStreamElementPayloadProtos {
  private BallFieldSideStateStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface BallFieldSideStateStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.football.BallFieldSideStateStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Flag indicating if the ball is on the left side of the field
     * </pre>
     *
     * <code>bool ballOnLeftSide = 1;</code>
     */
    boolean getBallOnLeftSide();
  }
  /**
   * <pre>
   * Payload of a ballFieldSideState stream element
   * TODO: The Ball Field Side Worker is a relatively useless worker that was only introduced to check if StreamTeam can perform Deep Learning based analyses. Remove this file as soon as there is a more meaningful Deep Learning based analysis worker.
   * </pre>
   *
   * Protobuf type {@code streamTeam.football.BallFieldSideStateStreamElementPayload}
   */
  public  static final class BallFieldSideStateStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.football.BallFieldSideStateStreamElementPayload)
      BallFieldSideStateStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use BallFieldSideStateStreamElementPayload.newBuilder() to construct.
    private BallFieldSideStateStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private BallFieldSideStateStreamElementPayload() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new BallFieldSideStateStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private BallFieldSideStateStreamElementPayload(
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

              ballOnLeftSide_ = input.readBool();
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
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.Builder.class);
    }

    public static final int BALLONLEFTSIDE_FIELD_NUMBER = 1;
    private boolean ballOnLeftSide_;
    /**
     * <pre>
     * Flag indicating if the ball is on the left side of the field
     * </pre>
     *
     * <code>bool ballOnLeftSide = 1;</code>
     */
    public boolean getBallOnLeftSide() {
      return ballOnLeftSide_;
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
      if (ballOnLeftSide_ != false) {
        output.writeBool(1, ballOnLeftSide_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (ballOnLeftSide_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, ballOnLeftSide_);
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
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload) obj;

      if (getBallOnLeftSide()
          != other.getBallOnLeftSide()) return false;
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
      hash = (37 * hash) + BALLONLEFTSIDE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getBallOnLeftSide());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parseFrom(
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
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload prototype) {
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
     * Payload of a ballFieldSideState stream element
     * TODO: The Ball Field Side Worker is a relatively useless worker that was only introduced to check if StreamTeam can perform Deep Learning based analyses. Remove this file as soon as there is a more meaningful Deep Learning based analysis worker.
     * </pre>
     *
     * Protobuf type {@code streamTeam.football.BallFieldSideStateStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.football.BallFieldSideStateStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.newBuilder()
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
        ballOnLeftSide_ = false;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload(this);
        result.ballOnLeftSide_ = ballOnLeftSide_;
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
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.getDefaultInstance()) return this;
        if (other.getBallOnLeftSide() != false) {
          setBallOnLeftSide(other.getBallOnLeftSide());
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
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private boolean ballOnLeftSide_ ;
      /**
       * <pre>
       * Flag indicating if the ball is on the left side of the field
       * </pre>
       *
       * <code>bool ballOnLeftSide = 1;</code>
       */
      public boolean getBallOnLeftSide() {
        return ballOnLeftSide_;
      }
      /**
       * <pre>
       * Flag indicating if the ball is on the left side of the field
       * </pre>
       *
       * <code>bool ballOnLeftSide = 1;</code>
       */
      public Builder setBallOnLeftSide(boolean value) {
        
        ballOnLeftSide_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Flag indicating if the ball is on the left side of the field
       * </pre>
       *
       * <code>bool ballOnLeftSide = 1;</code>
       */
      public Builder clearBallOnLeftSide() {
        
        ballOnLeftSide_ = false;
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


      // @@protoc_insertion_point(builder_scope:streamTeam.football.BallFieldSideStateStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.football.BallFieldSideStateStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<BallFieldSideStateStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<BallFieldSideStateStreamElementPayload>() {
      @java.lang.Override
      public BallFieldSideStateStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BallFieldSideStateStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<BallFieldSideStateStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<BallFieldSideStateStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nRsrc/main/protobuf/streamTeam/football/" +
      "ballFieldSideStateStreamElementPayload.p" +
      "roto\022\023streamTeam.football\"@\n&BallFieldSi" +
      "deStateStreamElementPayload\022\026\n\016ballOnLef" +
      "tSide\030\001 \001(\010Br\nBch.unibas.dmi.dbis.stream" +
      "Team.dataStreamElements.protobuf.footbal" +
      "lB,BallFieldSideStateStreamElementPayloa" +
      "dProtosb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_football_BallFieldSideStateStreamElementPayload_descriptor,
        new java.lang.String[] { "BallOnLeftSide", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
