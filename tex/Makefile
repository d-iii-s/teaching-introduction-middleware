.PHONY: all clean

OUTPUT = out

COMPONENTS = $(basename $(wildcard [0-9][0-9]*.tex))
ALL_BEAMERS = $(addsuffix -beamer.pdf, $(COMPONENTS))
ALL_ARTICLES = $(addsuffix -article.pdf, $(COMPONENTS))
OUT_BEAMERS = $(addprefix $(OUTPUT)/, $(ALL_BEAMERS))
OUT_ARTICLES = $(addprefix $(OUTPUT)/, $(ALL_ARTICLES))
VIEW_BEAMERS = $(addprefix view-, $(ALL_BEAMERS))
VIEW_ARTICLES = $(addprefix view-, $(ALL_ARTICLES))

.PHONY: $(VIEW_BEAMERS) $(VIEW_ARTICLES)

LATEXMK_VIEW = -pvc -new-viewer -interaction=nonstopmode
LATEXMK_BEAMER = -outdir="$(OUTPUT)" -usepretex -pretex="\def\dobeamer{}" -lualatex -jobname=%A-beamer
LATEXMK_ARTICLE = -outdir="$(OUTPUT)" -usepretex -pretex="\def\doarticle{}" -lualatex -jobname=%A-article

all: $(OUT_BEAMERS) $(OUT_ARTICLES)

clean:
	rm -rf $(OUTPUT)

$(OUTPUT)/%-beamer.pdf: %.tex
	latexmk $(LATEXMK_BEAMER) $<

$(OUTPUT)/%-article.pdf: %.tex
	latexmk $(LATEXMK_ARTICLE) $<

$(VIEW_BEAMERS): view-%-beamer.pdf: %.tex
	latexmk $(LATEXMK_VIEW) $(LATEXMK_BEAMER) $<

$(VIEW_ARTICLES): view-%-article.pdf: %.tex
	latexmk $(LATEXMK_VIEW) $(LATEXMK_ARTICLE) $<
