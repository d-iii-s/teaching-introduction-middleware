.PHONY: all clean

# Settings

MAKEFLAGS += --no-builtin-rules --no-builtin-variables

PC = $(shell pkg-config --variable=prefix protobuf)/bin/protoc
PC_OPTS =
CC = g++
CC_OPTS = -std=c++11 -O0 -g $(shell pkg-config --cflags protobuf) -Wall -Wextra -Wno-unused-parameter -Werror
LD = g++
LD_OPTS = -std=c++11 -O0 -g $(shell pkg-config --libs protobuf)

# Files

ALL_EX = example
ALL_OO = $(addsuffix .o, $(ALL_EX))
ALL_DD = $(addsuffix .dep, $(ALL_EX))
ALL_PB = $(addsuffix .pb.h, $(ALL_EX)) $(addsuffix .pb.cc, $(ALL_EX))

# Rules

all: $(ALL_EX)

clean:
	rm -f *.o
	rm -f *.dep
	rm -f $(ALL_PB)
	rm -f $(ALL_EX)

example: example.o example.pb.o
	$(LD) $^ $(LD_OPTS) -o $@

example.cc: example.pb.h

%.o: %.cc
	$(CC) $(CC_OPTS) -c -o $@ $<

%.pb.h %.pb.cc: %.proto
	$(PC) $(PC_OPTS) --cpp_out="." example.proto

# Deps

-include $(ALL_DD)

%.dep: %.cc
	$(CC) $(CC_OPTS) -MM -MT "$@ $(basename $@).o" -o $@ $<
