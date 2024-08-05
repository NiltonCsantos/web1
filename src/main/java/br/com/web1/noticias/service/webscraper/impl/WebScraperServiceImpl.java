package br.com.web1.noticias.service.webscraper.impl;

import br.com.web1.noticias.model.CategoriaEntidade;
import br.com.web1.noticias.repository.CategoriaRepositoy;
import br.com.web1.noticias.service.rss.RssService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebScraperServiceImpl {

    private static final String TARGET_URL = "https://g1.globo.com/dynamo/rss2.xml";

    private final RssService rssService;

    private final CategoriaRepositoy categoriaRepositoy;

    @Scheduled(fixedRate = 3600000) // Executa a cada hora (3600000 ms)
    public void scrapeData() throws Exception {
        try {
            var categoriasNomes = categoriaRepositoy.buscarNomeCategorias();



            // Conectar e obter o documento
            Document doc = Jsoup.connect("https://g1.globo.com/tecnologia/noticia/2012/11/siga-o-g1-por-rss.html").get();

            // Selecionar o div com a classe materia-conteudo
            Element materiaConteudo = doc.selectFirst(".materia-conteudo");

            findSpecificLink(materiaConteudo, categoriasNomes);

            if (materiaConteudo != null) {
                // Encontrar todos os elementos .olho.componente_materia.expandido
                Elements sections = materiaConteudo.select(".olho.componente_materia.expandido");

                if (sections.size() >= 2) {
                    // Obter a seção de editorias
                    Element editoriasSection = sections.get(0);
                    Element regioesSection = sections.get(1);

                    // Processar e imprimir Editorias
                    System.out.println("Editorias Section:");
                    Elements editoriasElements = getNextLinks(editoriasSection);

                    for (Element link : editoriasElements) {
                        String title = link.previousElementSibling().text();
                        String url = link.absUrl("href");

                        if (!categoriasNomes.contains(title.trim().toUpperCase())){
                            var categoria = CategoriaEntidade.builder()
                                    .catBlEditoria(true)
                                    .catTxNome(title.trim().toUpperCase())
                                    .catTxUrl(url)
                                    .build();

                            var nomeCategoria = categoriaRepositoy.save(categoria).getCatTxNome();
                            categoriasNomes.add(nomeCategoria);
                        }

                        rssService.buscarRss(url, title.trim().toUpperCase());
                    }

                    // Processar e imprimir Regiões
                    System.out.println("Regiões Section:");
                    Elements regioesElements = getNextLinks(regioesSection);
                    for (Element link : regioesElements) {
                        String title = link.previousElementSibling().text();
                        String url = link.absUrl("href");
                        System.out.println("Title: " + title);
                        System.out.println("URL: " + url);


                        if (!categoriasNomes.contains(title.trim().toUpperCase())){
                            var categoria = CategoriaEntidade.builder()
                                    .catBlEditoria(false)
                                    .catTxNome(title.trim().toUpperCase())
                                    .catTxUrl(url)
                                    .build();
                            var nomeCategoria = categoriaRepositoy.save(categoria).getCatTxNome();
                            categoriasNomes.add(nomeCategoria);
                        }

                        rssService.buscarRss(url, title.trim().toUpperCase());
                    }


                } else {
                    System.out.println("Não foram encontradas as seções necessárias.");
                }
            } else {
                System.out.println("Elemento com a classe 'materia-conteudo' não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Elements getNextLinks(Element section) {
        Elements links = new Elements();
        Element next = section.nextElementSibling();
        while (next != null && !next.hasClass("olho")) {
            links.addAll(next.select("a"));
            next = next.nextElementSibling();
        }
        return links;
    }

    private void findSpecificLink(Element container, List<String> categoriasNomes) {
        Elements links = container.select("a");
        for (Element link : links) {
            if (TARGET_URL.equals(link.absUrl("href"))) {

                String url = link.absUrl("href");
                String title = link.previousElementSibling().text();
                if (!categoriasNomes.contains(title.trim().toUpperCase())){
                    var categoria = CategoriaEntidade.builder()
                            .catBlEditoria(false)
                            .catTxNome(title.trim().toUpperCase())
                            .catTxUrl(url)
                            .build();
                    var nomeCategoria = categoriaRepositoy.save(categoria).getCatTxNome();
                    categoriasNomes.add(nomeCategoria);
                }

                rssService.buscarRss(url, title);
                break;// Retorna o link encontrado
            }
        }
        // Retorna uma mensagem se o link não for encontrado
    }
}
