/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.rendering.nui.mainMenu.videoSettings;

import org.terasology.config.RenderingConfig;
import org.terasology.rendering.nui.databinding.Binding;

/**
 * @author Immortius
 */
public class VideoQualityBinding implements Binding<VideoQuality> {
    private RenderingConfig config;

    public VideoQualityBinding(RenderingConfig config) {
        this.config = config;
    }

    @Override
    public VideoQuality get() {
        if (config.isFlickeringLight() && config.isVignette() && config.isEyeAdaptation() && config.isFilmGrain()) {
            if (config.isSsao()) {
                if (config.isBloom() && config.isMotionBlur() && config.isLightShafts() && config.isCloudShadows()) {
                    return VideoQuality.UBER;
                }
            } else if (config.isCloudShadows()) {
                if (config.isBloom() && config.isMotionBlur() && config.isLightShafts()) {
                    return VideoQuality.INSANE;
                }
            } else if (config.isBloom()) {
                if (!config.isMotionBlur() && !config.isLightShafts()) {
                    return VideoQuality.EPIC;
                }
            } else if (!config.isMotionBlur() && !config.isLightShafts()) {
                return VideoQuality.NICE;
            }
        }
        return VideoQuality.CUSTOM;
    }

    @Override
    public void set(VideoQuality value) {
        value.apply(config);
    }
}
