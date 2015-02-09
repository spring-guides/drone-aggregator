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

package io.spring.guide.drone

@Grab('org.jsoup:jsoup:1.6.1')

import org.jsoup.nodes.Document
import org.jsoup.Jsoup

/**
 * Display drone status icons
 *
 * @author Greg Turnquist
 */
@Grab("thymeleaf-spring4")
@Grab("spring-web")
@Controller
@Log
class DroneAggregator {

    @RequestMapping("/")
    String index(Map<String, Object> model) {

        // Scan for all guides
        Document doc = Jsoup.connect("http://spring.io/guides").get()

        def guides = doc.select("a.guide--title")
                .findAll { !it.attr("href").contains("tutorials") }
                .collect {
            [name: "gs-" + (it.attr("href") - "/guides/gs/" - "/"),
                    href: it.attr("href"),
                    title: it.text()]
        }

        model.put("guides", guides)

        "home"
    }

}
