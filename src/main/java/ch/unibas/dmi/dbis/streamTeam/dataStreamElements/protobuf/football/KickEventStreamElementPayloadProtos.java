// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/football/kickEventStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football;

public final class KickEventStreamElementPayloadProtos {
  private KickEventStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface KickEventStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.football.KickEventStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Number of players of the opposing team which are nearer to the goal
     * </pre>
     *
     * <code>int32 numPlayersNearerToGoal = 1;</code>
     */
    int getNumPlayersNearerToGoal();

    /**
     * <pre>
     * Specifies if the player who kicked the ball was attacked during the kick
     * </pre>
     *
     * <code>bool attacked = 2;</code>
     */
    boolean getAttacked();

    /**
     * <pre>
     * Specifies the zone in which the ball was kicked (outside, left, center, right)
     * </pre>
     *
     * <code>string zone = 3;</code>
     */
    java.lang.String getZone();
    /**
     * <pre>
     * Specifies the zone in which the ball was kicked (outside, left, center, right)
     * </pre>
     *
     * <code>string zone = 3;</code>
     */
    com.google.protobuf.ByteString
        getZoneBytes();
  }
  /**
   * <pre>
   * Payload of a kickEvent stream element
   * </pre>
   *
   * Protobuf type {@code streamTeam.football.KickEventStreamElementPayload}
   */
  public  static final class KickEventStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.football.KickEventStreamElementPayload)
      KickEventStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use KickEventStreamElementPayload.newBuilder() to construct.
    private KickEventStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private KickEventStreamElementPayload() {
      zone_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new KickEventStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private KickEventStreamElementPayload(
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

              numPlayersNearerToGoal_ = input.readInt32();
              break;
            }
            case 16: {

              attacked_ = input.readBool();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              zone_ = s;
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
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.internal_static_streamTeam_football_KickEventStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.Builder.class);
    }

    public static final int NUMPLAYERSNEARERTOGOAL_FIELD_NUMBER = 1;
    private int numPlayersNearerToGoal_;
    /**
     * <pre>
     * Number of players of the opposing team which are nearer to the goal
     * </pre>
     *
     * <code>int32 numPlayersNearerToGoal = 1;</code>
     */
    public int getNumPlayersNearerToGoal() {
      return numPlayersNearerToGoal_;
    }

    public static final int ATTACKED_FIELD_NUMBER = 2;
    private boolean attacked_;
    /**
     * <pre>
     * Specifies if the player who kicked the ball was attacked during the kick
     * </pre>
     *
     * <code>bool attacked = 2;</code>
     */
    public boolean getAttacked() {
      return attacked_;
    }

    public static final int ZONE_FIELD_NUMBER = 3;
    private volatile java.lang.Object zone_;
    /**
     * <pre>
     * Specifies the zone in which the ball was kicked (outside, left, center, right)
     * </pre>
     *
     * <code>string zone = 3;</code>
     */
    public java.lang.String getZone() {
      java.lang.Object ref = zone_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        zone_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * Specifies the zone in which the ball was kicked (outside, left, center, right)
     * </pre>
     *
     * <code>string zone = 3;</code>
     */
    public com.google.protobuf.ByteString
        getZoneBytes() {
      java.lang.Object ref = zone_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        zone_ = b;
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
      if (numPlayersNearerToGoal_ != 0) {
        output.writeInt32(1, numPlayersNearerToGoal_);
      }
      if (attacked_ != false) {
        output.writeBool(2, attacked_);
      }
      if (!getZoneBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, zone_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (numPlayersNearerToGoal_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, numPlayersNearerToGoal_);
      }
      if (attacked_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, attacked_);
      }
      if (!getZoneBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, zone_);
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
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload) obj;

      if (getNumPlayersNearerToGoal()
          != other.getNumPlayersNearerToGoal()) return false;
      if (getAttacked()
          != other.getAttacked()) return false;
      if (!getZone()
          .equals(other.getZone())) return false;
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
      hash = (37 * hash) + NUMPLAYERSNEARERTOGOAL_FIELD_NUMBER;
      hash = (53 * hash) + getNumPlayersNearerToGoal();
      hash = (37 * hash) + ATTACKED_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getAttacked());
      hash = (37 * hash) + ZONE_FIELD_NUMBER;
      hash = (53 * hash) + getZone().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parseFrom(
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
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload prototype) {
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
     * Payload of a kickEvent stream element
     * </pre>
     *
     * Protobuf type {@code streamTeam.football.KickEventStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.football.KickEventStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.internal_static_streamTeam_football_KickEventStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.newBuilder()
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
        numPlayersNearerToGoal_ = 0;

        attacked_ = false;

        zone_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload(this);
        result.numPlayersNearerToGoal_ = numPlayersNearerToGoal_;
        result.attacked_ = attacked_;
        result.zone_ = zone_;
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
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.getDefaultInstance()) return this;
        if (other.getNumPlayersNearerToGoal() != 0) {
          setNumPlayersNearerToGoal(other.getNumPlayersNearerToGoal());
        }
        if (other.getAttacked() != false) {
          setAttacked(other.getAttacked());
        }
        if (!other.getZone().isEmpty()) {
          zone_ = other.zone_;
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
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int numPlayersNearerToGoal_ ;
      /**
       * <pre>
       * Number of players of the opposing team which are nearer to the goal
       * </pre>
       *
       * <code>int32 numPlayersNearerToGoal = 1;</code>
       */
      public int getNumPlayersNearerToGoal() {
        return numPlayersNearerToGoal_;
      }
      /**
       * <pre>
       * Number of players of the opposing team which are nearer to the goal
       * </pre>
       *
       * <code>int32 numPlayersNearerToGoal = 1;</code>
       */
      public Builder setNumPlayersNearerToGoal(int value) {
        
        numPlayersNearerToGoal_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Number of players of the opposing team which are nearer to the goal
       * </pre>
       *
       * <code>int32 numPlayersNearerToGoal = 1;</code>
       */
      public Builder clearNumPlayersNearerToGoal() {
        
        numPlayersNearerToGoal_ = 0;
        onChanged();
        return this;
      }

      private boolean attacked_ ;
      /**
       * <pre>
       * Specifies if the player who kicked the ball was attacked during the kick
       * </pre>
       *
       * <code>bool attacked = 2;</code>
       */
      public boolean getAttacked() {
        return attacked_;
      }
      /**
       * <pre>
       * Specifies if the player who kicked the ball was attacked during the kick
       * </pre>
       *
       * <code>bool attacked = 2;</code>
       */
      public Builder setAttacked(boolean value) {
        
        attacked_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Specifies if the player who kicked the ball was attacked during the kick
       * </pre>
       *
       * <code>bool attacked = 2;</code>
       */
      public Builder clearAttacked() {
        
        attacked_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object zone_ = "";
      /**
       * <pre>
       * Specifies the zone in which the ball was kicked (outside, left, center, right)
       * </pre>
       *
       * <code>string zone = 3;</code>
       */
      public java.lang.String getZone() {
        java.lang.Object ref = zone_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          zone_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * Specifies the zone in which the ball was kicked (outside, left, center, right)
       * </pre>
       *
       * <code>string zone = 3;</code>
       */
      public com.google.protobuf.ByteString
          getZoneBytes() {
        java.lang.Object ref = zone_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          zone_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * Specifies the zone in which the ball was kicked (outside, left, center, right)
       * </pre>
       *
       * <code>string zone = 3;</code>
       */
      public Builder setZone(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        zone_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Specifies the zone in which the ball was kicked (outside, left, center, right)
       * </pre>
       *
       * <code>string zone = 3;</code>
       */
      public Builder clearZone() {
        
        zone_ = getDefaultInstance().getZone();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Specifies the zone in which the ball was kicked (outside, left, center, right)
       * </pre>
       *
       * <code>string zone = 3;</code>
       */
      public Builder setZoneBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        zone_ = value;
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


      // @@protoc_insertion_point(builder_scope:streamTeam.football.KickEventStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.football.KickEventStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<KickEventStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<KickEventStreamElementPayload>() {
      @java.lang.Override
      public KickEventStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new KickEventStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<KickEventStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<KickEventStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos.KickEventStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_football_KickEventStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nIsrc/main/protobuf/streamTeam/football/" +
      "kickEventStreamElementPayload.proto\022\023str" +
      "eamTeam.football\"_\n\035KickEventStreamEleme" +
      "ntPayload\022\036\n\026numPlayersNearerToGoal\030\001 \001(" +
      "\005\022\020\n\010attacked\030\002 \001(\010\022\014\n\004zone\030\003 \001(\tBi\nBch." +
      "unibas.dmi.dbis.streamTeam.dataStreamEle" +
      "ments.protobuf.footballB#KickEventStream" +
      "ElementPayloadProtosb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_football_KickEventStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_football_KickEventStreamElementPayload_descriptor,
        new java.lang.String[] { "NumPlayersNearerToGoal", "Attacked", "Zone", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}