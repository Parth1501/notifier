package com.job.scrapper.service;

import com.job.scrapper.model.JobData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Scrapper {
    public List<List<String>> scrapArogyaSathi() {
        String url="https://arogyasathi.gujarat.gov.in/CurrentOpenings.aspx";
        List<List<String>> res=new ArrayList<>();
        try {
            // Fetch the HTML document from the given URL
            Document document = Jsoup.connect(url).get();

            // Example: Get the title of the page

            // Example: Select specific elements using CSS selectors
            var table = document.getElementById("dynamic-table"); // Select all <p> elements
            var tableData=new ArrayList<List<String>>();
            if (table != null) {
                // Get the table rows
                Elements rows = table.select("tr");

                // Loop through the rows and extract the data
                for (Element row : rows) {
                    List<String> rowData = new ArrayList<>();
                    Elements columns = row.select("th, td"); // Select both <th> and <td> for headers and data

                    for (Element column : columns) {
                        rowData.add(column.text());
                    }

                    tableData.add(rowData);
                }
            }

            // Loop through the paragraphs and extract the text
            for(var row: tableData) {
                var postName=row.get(6);
                if(postName.toLowerCase().contains("physio")) {
                    res.add(row);
                }
            }
//            log.info("Data from scrapping \n{}", tableData);
        } catch (IOException e) {
            log.error("Unable to fetch the data ",e);
        }
        return res;
    }

    public List<JobData> scrapGoogleJob() {
        // 3 days: https://www.google.com/search?sca_esv=e44d5ba731f3a8e3&sca_upv=1&sxsrf=ADLYWILoDavJgPH35l9dnSJ67vW40YY7lQ%3A1727089159479&q=physiotherapy%20job%20posting%20surat%20in%20the%20last%203%20days&uds=ADvngMjcH0KdF7qGWtwTBrP0nt7dRHZs_3UxVkBT2e_bKJy3gjt2kPnVG89vLBL2NQakbL_Sc4mvUhdhgw4YS5VJXWs7riUgpHk-7HdYx_Gqd1DwHdRUAvWv_hC7LhsVNhiq0C_dqf0SGWiQFtBzetubbaL_czXAbyCpI-W2Rt9lg20undHhgM_pEQYncGY7-ACdnjANRp0_8aBSRp5NJtJS9_zvHRJgLzmiN1p4yVElF1C8b7q9USvERGR7YuYw1pCy6VYntuW20EYpvEu9sqDEzp_z7wqCp_pNdJ04cGH7cNN_ibWG2hlvdMaAgzq24dHB2ck72PIUXkQmPgATxCkLF7PV-j5gqg&sa=X&ved=2ahUKEwjA0JfB9NiIAxVN7TgGHSyAHIEQ3L8LegQIHxAN&jbr=sep:0&udm=8
        // 1 day: https://www.google.com/search?client=ubuntu-sn&sca_esv=37afbf05fd6f097e&sca_upv=1&channel=fs&biw=1680&bih=427&q=physiotherapy+job+posting+surat+since+yesterday&uds=ADvngMjcH0KdF7qGWtwTBrP0nt7daSGmSjLTAH-oRzMvJ5-IJgVxBISlWgvCsGRrRw3-Tg0T_JypLLFpTbSoOihNAQFySVbmthZ9GVT3Piu-mTgV5n1gsDnhN5eFo5C52ojzBoJnSjZfJunrrJHFwAy3RcxZZOury3ErVWwsSdhjQJ2-ZISa8-a7x7KSFlv5kVjito9XItGFGH-ahDuC06EVifFBPu8z7Z6hdlafsUNeMJawVkROOPZuO0eex-CSu4FQ2IRcsimK3YWt3n-IOxf937wJTN2na0JSMYZH6H19aJKxGaKPhnTl9MnxjcYBhe-INpgkHXUYPZP7dfj4uSKv-Qvf_xSamg&udm=8&sa=X&ved=2ahUKEwig9MatmdSIAxUxhq8BHcXeB7kQkbEKegQICxAE
        String url="https://www.google.com/search?client=ubuntu-sn&hs=HD6&sca_esv=445c9ec50e6b4223&sca_upv=1&channel=fs&biw=1680&bih=921&q=physiotherapy+job+posting+in+the+last+3+days+near+Surat,+Gujarat+in+the+last+3+days&uds=ADvngMgvwjThEGn1QzCbykcnfc2RaXCi3m_y-OFkWLJlrRuPYhtbhPQ1VJrn2e4W3qcHBMmWlufYdSkeS4YUIf45qQ1KxgkUc24y9H7s0rdnmvoTEqlg244OhgctMpgTTT5ESHcs4s44rr_Viop8wnj-UMcq82lOFxVVRLlCnQqigN0LcDF8BDduDp0zLdSUI5aEalEcOBkmxyuN_BD5Ep2rsU6GpTZf-PL-E0Ss6w98ZW3AAuO3ItkfHKpbG80qg0LtJACDiXSOW4m0bXmLZI8b-oQtqdWxMyTLxVO9cjpD4XC1uhdEl-JGEU2IZeK7cFDzOgGRjU5se79a0Ym5zPpta0nu4uVdVoy0_XO22CEPacDYNfQnvgg&udm=8&sa=X&ved=2ahUKEwjy54uO99iIAxV9n2MGHb2OJx4QkbEKegQIDBAE&jbr=sep:0";
        List<JobData> res=new ArrayList<>();
        try {
            // Fetch the HTML document from the given URL
            Document document = Jsoup.connect(url).get();

            // Example: Get the title of the page

            // Example: Select specific elements using CSS selectors
            var jobNames=document.select(".tNxQIb.PUpOsf");
            var institute=document.select(".wHYlTd.MKCbgd.a3jPc");
            var locations=document.select(".wHYlTd.FqK3wc.MKCbgd");

            var size=jobNames.size();
            for(int i=0;i<size;i++) {
                JobData newData=new JobData();
                newData.setGoogleUrl(url);
                newData.setJobName(jobNames.get(i).text());
                newData.setInstitute(institute.get(i).text());
                newData.setCity(locations.get(i).text());
                res.add(newData);
            }
        } catch (IOException e) {
            log.error("Unable to fetch the data ",e);
        }
        return res;
    }
}
