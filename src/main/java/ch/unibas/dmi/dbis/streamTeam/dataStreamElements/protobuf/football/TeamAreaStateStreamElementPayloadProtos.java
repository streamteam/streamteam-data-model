// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/protobuf/streamTeam/football/teamAreaStateStreamElementPayload.proto

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football;

public final class TeamAreaStateStreamElementPayloadProtos {
  private TeamAreaStateStreamElementPayloadProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TeamAreaStateStreamElementPayloadOrBuilder extends
      // @@protoc_insertion_point(interface_extends:streamTeam.football.TeamAreaStateStreamElementPayload)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Surface of the minimum bounding rectangle around the players of the team (in m^2)
     * </pre>
     *
     * <code>double mbrSurface = 1;</code>
     */
    double getMbrSurface();

    /**
     * <pre>
     * Surface of the planar convex hull around the players of the team (in m^2)
     * </pre>
     *
     * <code>double pchSurface = 2;</code>
     */
    double getPchSurface();
  }
  /**
   * <pre>
   * Payload of a teamAreaState stream element
   * </pre>
   *
   * Protobuf type {@code streamTeam.football.TeamAreaStateStreamElementPayload}
   */
  public  static final class TeamAreaStateStreamElementPayload extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:streamTeam.football.TeamAreaStateStreamElementPayload)
      TeamAreaStateStreamElementPayloadOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use TeamAreaStateStreamElementPayload.newBuilder() to construct.
    private TeamAreaStateStreamElementPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private TeamAreaStateStreamElementPayload() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new TeamAreaStateStreamElementPayload();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private TeamAreaStateStreamElementPayload(
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
            case 9: {

              mbrSurface_ = input.readDouble();
              break;
            }
            case 17: {

              pchSurface_ = input.readDouble();
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
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.Builder.class);
    }

    public static final int MBRSURFACE_FIELD_NUMBER = 1;
    private double mbrSurface_;
    /**
     * <pre>
     * Surface of the minimum bounding rectangle around the players of the team (in m^2)
     * </pre>
     *
     * <code>double mbrSurface = 1;</code>
     */
    public double getMbrSurface() {
      return mbrSurface_;
    }

    public static final int PCHSURFACE_FIELD_NUMBER = 2;
    private double pchSurface_;
    /**
     * <pre>
     * Surface of the planar convex hull around the players of the team (in m^2)
     * </pre>
     *
     * <code>double pchSurface = 2;</code>
     */
    public double getPchSurface() {
      return pchSurface_;
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
      if (mbrSurface_ != 0D) {
        output.writeDouble(1, mbrSurface_);
      }
      if (pchSurface_ != 0D) {
        output.writeDouble(2, pchSurface_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (mbrSurface_ != 0D) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(1, mbrSurface_);
      }
      if (pchSurface_ != 0D) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(2, pchSurface_);
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
      if (!(obj instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload)) {
        return super.equals(obj);
      }
      ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload other = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload) obj;

      if (java.lang.Double.doubleToLongBits(getMbrSurface())
          != java.lang.Double.doubleToLongBits(
              other.getMbrSurface())) return false;
      if (java.lang.Double.doubleToLongBits(getPchSurface())
          != java.lang.Double.doubleToLongBits(
              other.getPchSurface())) return false;
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
      hash = (37 * hash) + MBRSURFACE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getMbrSurface()));
      hash = (37 * hash) + PCHSURFACE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          java.lang.Double.doubleToLongBits(getPchSurface()));
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parseFrom(
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
    public static Builder newBuilder(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload prototype) {
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
     * Payload of a teamAreaState stream element
     * </pre>
     *
     * Protobuf type {@code streamTeam.football.TeamAreaStateStreamElementPayload}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:streamTeam.football.TeamAreaStateStreamElementPayload)
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayloadOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.class, ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.Builder.class);
      }

      // Construct using ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.newBuilder()
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
        mbrSurface_ = 0D;

        pchSurface_ = 0D;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload getDefaultInstanceForType() {
        return ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.getDefaultInstance();
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload build() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload buildPartial() {
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload result = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload(this);
        result.mbrSurface_ = mbrSurface_;
        result.pchSurface_ = pchSurface_;
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
        if (other instanceof ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload) {
          return mergeFrom((ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload other) {
        if (other == ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.getDefaultInstance()) return this;
        if (other.getMbrSurface() != 0D) {
          setMbrSurface(other.getMbrSurface());
        }
        if (other.getPchSurface() != 0D) {
          setPchSurface(other.getPchSurface());
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
        ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private double mbrSurface_ ;
      /**
       * <pre>
       * Surface of the minimum bounding rectangle around the players of the team (in m^2)
       * </pre>
       *
       * <code>double mbrSurface = 1;</code>
       */
      public double getMbrSurface() {
        return mbrSurface_;
      }
      /**
       * <pre>
       * Surface of the minimum bounding rectangle around the players of the team (in m^2)
       * </pre>
       *
       * <code>double mbrSurface = 1;</code>
       */
      public Builder setMbrSurface(double value) {
        
        mbrSurface_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Surface of the minimum bounding rectangle around the players of the team (in m^2)
       * </pre>
       *
       * <code>double mbrSurface = 1;</code>
       */
      public Builder clearMbrSurface() {
        
        mbrSurface_ = 0D;
        onChanged();
        return this;
      }

      private double pchSurface_ ;
      /**
       * <pre>
       * Surface of the planar convex hull around the players of the team (in m^2)
       * </pre>
       *
       * <code>double pchSurface = 2;</code>
       */
      public double getPchSurface() {
        return pchSurface_;
      }
      /**
       * <pre>
       * Surface of the planar convex hull around the players of the team (in m^2)
       * </pre>
       *
       * <code>double pchSurface = 2;</code>
       */
      public Builder setPchSurface(double value) {
        
        pchSurface_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Surface of the planar convex hull around the players of the team (in m^2)
       * </pre>
       *
       * <code>double pchSurface = 2;</code>
       */
      public Builder clearPchSurface() {
        
        pchSurface_ = 0D;
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


      // @@protoc_insertion_point(builder_scope:streamTeam.football.TeamAreaStateStreamElementPayload)
    }

    // @@protoc_insertion_point(class_scope:streamTeam.football.TeamAreaStateStreamElementPayload)
    private static final ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload();
    }

    public static ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TeamAreaStateStreamElementPayload>
        PARSER = new com.google.protobuf.AbstractParser<TeamAreaStateStreamElementPayload>() {
      @java.lang.Override
      public TeamAreaStateStreamElementPayload parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TeamAreaStateStreamElementPayload(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<TeamAreaStateStreamElementPayload> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TeamAreaStateStreamElementPayload> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nMsrc/main/protobuf/streamTeam/football/" +
      "teamAreaStateStreamElementPayload.proto\022" +
      "\023streamTeam.football\"K\n!TeamAreaStateStr" +
      "eamElementPayload\022\022\n\nmbrSurface\030\001 \001(\001\022\022\n" +
      "\npchSurface\030\002 \001(\001Bm\nBch.unibas.dmi.dbis." +
      "streamTeam.dataStreamElements.protobuf.f" +
      "ootballB\'TeamAreaStateStreamElementPaylo" +
      "adProtosb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_streamTeam_football_TeamAreaStateStreamElementPayload_descriptor,
        new java.lang.String[] { "MbrSurface", "PchSurface", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}