package br.com.web1.noticias.service.rss.impl;

import br.com.web1.noticias.exception.NotFoundException;
import br.com.web1.noticias.model.NoticiaEntidade;
import br.com.web1.noticias.repository.CategoriaRepositoy;
import br.com.web1.noticias.repository.NoticiaRepository;
import br.com.web1.noticias.service.rss.RssService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RssServiceImpl implements RssService {

    private final NoticiaRepository noticiaRepository;

    private final CategoriaRepositoy categoriaRepositoy;

    @Override
    public void buscarRss(String catTxUrl, String catTxNome) {
        try {

            var categoria = categoriaRepositoy.findByCatTxNome(catTxNome)
                    .orElseThrow(() -> new NotFoundException("Categoria"));

            var listaNoticiasUrl = noticiaRepository.findAllByNotTxUrl();

            String rssUrl = catTxUrl;
            URL url = new URL(rssUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Create an XMLReader with the necessary configurations
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);

            // Use the custom XMLReader in SyndFeedInput
            SyndFeedInput input = new SyndFeedInput();
            InputSource source = new InputSource(new InputStreamReader(connection.getInputStream()));
            input.setAllowDoctypes(true);
            SyndFeed feed = input.build(source);


            // Acessando e processando as tags <item>
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                String title = entry.getTitle();
                String link = entry.getLink();
                String descricao = entry.getDescription() != null ? entry.getDescription().getValue() : "";
                String pubDateStr = entry.getPublishedDate().toString();

                // Define a custom DateTimeFormatter for parsing the pubDate
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                // Parse the published date
                LocalDateTime pubDate = LocalDateTime.parse(pubDateStr, inputFormatter);

                // Format the date to the desired output
                String formattedPubDate = pubDate.format(outputFormatter);


                String imgSrc = null;
                String textoRestante = null;
                if (descricao.contains("<img")) {
                    int imgStart = descricao.indexOf("<img");
                    int srcStart = descricao.indexOf("src=\"", imgStart) + 5;
                    int srcEnd = descricao.indexOf("\"", srcStart);
                    imgSrc = descricao.substring(srcStart, srcEnd);

                    int imgEnd = descricao.indexOf(">", imgStart) + 1;
                    textoRestante = descricao.substring(0, imgStart) + descricao.substring(imgEnd).trim();
                }

                if (!listaNoticiasUrl.contains(link)) {
                    var noticia = NoticiaEntidade.builder()
                            .categoria(categoria)
                            .notTxDescricao(textoRestante)
                            .notTxUrl(link.trim())
                            .notTxNome(title)
                            .notDtPublicao(pubDate)
                            .notTxImagem(imgSrc) // Supondo que você tenha um campo para a URL da imagem
                            .build();
                    noticiaRepository.save(noticia);
                }


            }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
