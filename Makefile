# Palesa Khoali
# KHLPAL002
# CSC2001F
# 19 April 2021

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= AccessAVLApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)
clean:
	rm $(BINDIR)/*.class
runAVL: $(CLASS_FILES)
	java -cp $(BINDIR) AccessAVLApp


