.PHONY: all clean

COMPONENTS = $(basename $(wildcard [0-9][0-9]*.tex))
ALL_BEAMERS = $(addsuffix -beamer.pdf, $(COMPONENTS))
ALL_ARTICLES = $(addsuffix -article.pdf, $(COMPONENTS))
VIEW_BEAMERS = $(addprefix view-, $(ALL_BEAMERS))
VIEW_ARTICLES = $(addprefix view-, $(ALL_ARTICLES))

.PHONY: $(VIEW_BEAMERS) $(VIEW_ARTICLES)

LATEXMK_VIEW = -pvc -new-viewer -interaction=nonstopmode
LATEXMK_BEAMER = -usepretex -pretex="\def\dobeamer{}" -lualatex -jobname=%A-beamer
LATEXMK_ARTICLE = -usepretex -pretex="\def\doarticle{}" -lualatex -jobname=%A-article

all: $(ALL_BEAMERS) $(ALL_ARTICLES)

clean:
	rm -f $(addsuffix .*, $(basename $(ALL_BEAMERS)))
	rm -f $(addsuffix .*, $(basename $(ALL_ARTICLES)))

%-beamer.pdf: %.tex
	latexmk $(LATEXMK_BEAMER) $<

%-article.pdf: %.tex
	latexmk $(LATEXMK_ARTICLE) $<

$(VIEW_BEAMERS): view-%-beamer.pdf: %.tex
	latexmk $(LATEXMK_VIEW) $(LATEXMK_BEAMER) $<

$(VIEW_ARTICLES): view-%-article.pdf: %.tex
	latexmk $(LATEXMK_VIEW) $(LATEXMK_ARTICLE) $<
