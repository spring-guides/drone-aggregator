/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.guide.travis

@Grab('org.jsoup:jsoup:1.6.1')

import org.jsoup.nodes.Document
import org.jsoup.Jsoup
import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Display travis status icons
 *
 * @author Greg Turnquist
 */
@Grab("thymeleaf-spring4")
@Grab("spring-web")
@Controller
@Log
class TravisAggregator {

    @Value('${org:spring-guides}')
    String org

    @Value('${site:http://spring.io}')
    String site

    @Value('${css.selector:a.guide--title}')
    String cssSelector

    @Value('${issue.site:http://issue-aggregator.guides.spring.io/}')
    String issueSite

    @Autowired
    Description description

    @RequestMapping("/")
    String index(Map<String, Object> model) {

        def guides = []

        description.sections.each { section ->

            // Scan for all guides
            Document doc = Jsoup.connect("${site}${section.section}").get()

            guides += doc.select(cssSelector)
                    .findAll { !it.attr("href").contains("tutorials") }
                    .collect {
                        [name: section.prefix + (it.attr("href") - section.toStrip - "/"),
                        href: it.attr("href"),
                        title: it.text()]
            }

        }

        model.put("guides", guides)
        model.put("site", site)
        model.put("org", org)
        model.put('issueSite', issueSite)

        "home"
    }

}

@Component
@ConfigurationProperties(prefix="description")
class Description {
    List<Section> sections

    public String toString() {
        def results = ""
        sections.each { results += it.toString() }
    }
}

class Section {
    String prefix
    String toStrip
    String section

    public String toString() { "${prefix} ${toStrip} ${section}" }
}

