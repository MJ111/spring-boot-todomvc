package todoapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import todoapp.core.todos.application.TodoEditor;
import todoapp.core.todos.application.TodoFinder;
import todoapp.web.model.FeatureTogglesProperties;

/**
 * `5) 확장 기능 활성화` 요구사항을 구현해보세요.
 *
 * 확장 기능 활성화 Web API를 만들어보세요.
 *  - 지금까지 배웠던 스프링 MVC 애노테이션을 사용해서 만드실 수 있습니다.
 * 모델 클래스는 todoapp.web.model.FeatureTogglesProperties 를 사용하세요.
 * 모델 클래스를 애플리케이션 외부 환경설정(application.yml) 정보로 구성되도록 만들어보세요.
 *  - todoapp.web.model.SiteProperties 다루던 방법을 떠올려보세요.
 *
 * url: GET /api/feature-toggles
 * response body:
 *     {
 *         "auth": true,
 *             "onlineUsersCounter": false
 *     }
 */

@RestController
public class FeatureTogglesRestController {
	private FeatureTogglesProperties featureProperties;
	
	public FeatureTogglesRestController(FeatureTogglesProperties featureProperties) {
		this.featureProperties = featureProperties;
	}
	
	@GetMapping("/api/feature-toggles")
	public FeatureTogglesProperties update() {
		return featureProperties;
	}

	
	static class OnlineUsersCounterCommand {
		
		private boolean isOnline;
		
		public boolean isOnline() {
			return isOnline;
		}
	}
}
