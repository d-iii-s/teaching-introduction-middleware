.PHONY: all clean

# Settings

MAKEFLAGS += --no-builtin-rules --no-builtin-variables

PC = $(shell pkg-config --variable=prefix protobuf)/bin/protoc
PC_OPTS = --plugin=protoc-gen-grpc=$(shell pkg-config --variable=prefix grpc++)/bin/grpc_cpp_plugin
CC = g++
CC_OPTS = -std=c++11 -O0 -g $(shell pkg-config --cflags protobuf grpc++) -Wall -Wextra -Wno-unused-parameter -Werror
LD = g++
LD_OPTS = -std=c++11 -O0 -g $(shell pkg-config --libs protobuf grpc++)

# Files

ALL_EX = client server
ALL_OO = $(addsuffix .o, $(ALL_EX))
ALL_DD = $(addsuffix .dep, $(ALL_EX))

ALL_IF = example
ALL_PB = $(addsuffix .pb.h, $(ALL_IF)) $(addsuffix .pb.cc, $(ALL_IF)) $(addsuffix .grpc.pb.h, $(ALL_IF)) $(addsuffix .grpc.pb.cc, $(ALL_IF))

# Rules

all: $(ALL_EX)

clean:
	rm -f *.o
	rm -f *.dep
	rm -f $(ALL_PB)
	rm -f $(ALL_EX)

client: client.o example.pb.o example.grpc.pb.o
	$(LD) $^ $(LD_OPTS) -o $@

server: server.o example.pb.o example.grpc.pb.o
	$(LD) $^ $(LD_OPTS) -o $@

client.cc: example.pb.h example.grpc.pb.h

server.cc: example.pb.h example.grpc.pb.h

%.o: %.cc
	$(CC) $(CC_OPTS) -c -o $@ $<

%.pb.h %.pb.cc %.grpc.pb.h %.grpc.pb.cc: %.proto
	$(PC) $(PC_OPTS) --cpp_out="." --grpc_out="." example.proto

# Deps

-include $(ALL_DD)

%.dep: %.cc
	$(CC) $(CC_OPTS) -MM -MT "$@ $(basename $@).o" -o $@ $<
