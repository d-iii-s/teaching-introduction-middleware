.PHONY: all clean view-beams view-notes

all:
	latexmk -lualatex Course-Beams.tex
	latexmk -lualatex Course-Notes.tex

clean:
	latexmk -c -lualatex Course-Beams.tex
	latexmk -c -lualatex Course-Notes.tex

view-beams:
	latexmk -pvc -lualatex -interaction=nonstopmode Course-Beams.tex

view-notes:
	latexmk -pvc -lualatex -interaction=nonstopmode Course-Notes.tex
