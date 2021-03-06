package SDK;

import com.insightfinder.output.AnomalyModel;
import com.insightfinder.core.detection.MetricDetectionParameter;
import com.insightfinder.core.detection.MetricDetectionProcessor;
import com.insightfinder.core.train.MetricTrainingProcessor;
import com.insightfinder.core.train.UBLTrainingParameter;
import com.insightfinder.output.TrainingModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MetricTrainingProcessor.class})
public class TrainingAndDetection extends TestBase {

  private String trainingDataFile = "src/test/res/metricTraining/trainingData.csv";

  public TrainingModel createUBLModel() {
    String dataCSV = readDataFromFile(trainingDataFile);
    UBLTrainingParameter ublTrainingParameter = new UBLTrainingParameter(0.95, 3, 10, false);
    MetricTrainingProcessor metricTrainingProcessor = new MetricTrainingProcessor(dataCSV,
        ublTrainingParameter);
    return metricTrainingProcessor.train();
  }

  @Test
  public void testTraining() {
    TrainingModel trainingModel = createUBLModel();
    assert trainingModel != null && trainingModel.isValid();
  }

  @Test
  public void testDetection() {
    TrainingModel trainingModel = createUBLModel();
    String dataCSV = readDataFromFile(trainingDataFile);
    MetricDetectionParameter metricDetectionParameter = new MetricDetectionParameter(0.95, 1,
        false);
    MetricDetectionProcessor metricDetectionProcessor = new MetricDetectionProcessor(
        dataCSV, trainingModel, metricDetectionParameter);
    List<AnomalyModel> anomalyModelList = metricDetectionProcessor.UBLDetection();
    assert anomalyModelList != null;
  }
}
