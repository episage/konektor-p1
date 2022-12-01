
class Dokument{

    def testRunner, context

    Dokument(def testRunner, def context) {
        this.testRunner = testRunner
        this.context = context
    }

    DokumentBuilder zbudujXML(String folder, String file, def paramList) {
        def DokBuilder = new DokumentBuilder(
                folder: folder,
                file: file,
                testRunner: testRunner,
                context: context,
                mapaParametrow: paramList
        )

        DokBuilder.pobierzTrescPlikuXML()
        DokBuilder.podmienParametryPlikuXML()
        DokBuilder.podstawWartosciDomyslne()
		
        return DokBuilder
    }

    static class DokumentBuilder {
        String folder, file, tresc
        def context, testRunner
        def mapaParametrow

        DokumentBuilder pobierzTrescPlikuXML(){
            String projectDir = context.expand( '${projectDir}')
            String sciezkaDoDokumentow = new File( projectDir ).getAbsolutePath() + "/" + folder + "/"
            def path = new File( sciezkaDoDokumentow + file ).getAbsolutePath();
            tresc = new File( path ).getText('UTF-8')

            this
        }

        DokumentBuilder podmienParametryPlikuXML() {
            for (parametr in mapaParametrow) {
				podmienParametr(parametr.key, parametr.value.value)
            }
            this
        }

        DokumentBuilder podmienParametr(String nazwaParametru, String wartoscParametru) {
            def fixedPatter = "${'@@'+nazwaParametru+'@@'}"
            if (wartoscParametru == null) {
                wartoscParametru = ""
            }
            tresc = tresc.replaceAll(~/${fixedPatter}~\{((?!}~).)*}~/, wartoscParametru)
            this
        }

        private void podstawWartosciDomyslne(){
            tresc = tresc.replaceAll(/@@((?!@@).)*@@/, "")
                    .replace( "~{", "" )
                    .replace( "}~", "" )
        }
    }
}