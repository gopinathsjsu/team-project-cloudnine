import React, { useEffect, useContext, useState, useRef } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Card, CardContent } from '@mui/material';

import { Bar } from 'react-chartjs-2';

import AppContext from 'context';

import { Grid, Container } from '@mui/material';
import WeeklySchedule from 'components/WeeklySchedule/WeeklySchedule';
import Activities from 'components/Activities/Activities';

import util from 'util';
import Services from 'services';
import LoginRegistration from 'components/LoginRegistration/LoginRegistration';
import ProfileHeader from 'components/ProfileHeader/ProfileHeader';

function Chart(props) {
  const buttonGroupRange = {
    titles: ['day', 'week', 'month', 'year'],
    handleClick(e) {
      console.log(e.target);
      setSelectedPeriod(e.target.innerText.toLowerCase());
      setAtMount(false);
    }
  };

  // ---------------------------------------------

  const [refresh, setRefresh] = useState(true)
  const [atMount, setAtMount] = useState(true);
  const [selectedPeriod, setSelectedPeriod] = useState(buttonGroupRange.titles[0]);
  const [chartDataTimeSpent, setChartDataTimeSpent] = useState(null);
  const { context, setContext } = useContext(AppContext);

  const refChart = useRef();

  const services = new Services(context, setContext);

  useEffect(() => {
    services.getTimeSpentStats(
      props.profile.id,
      selectedPeriod,
      (res) => {
        const chartData = {
          labels: [],
          datasets: [{
            label: 'Gymming Hours',
            data: [],
            borderWidth: 1
          }]
        };

        res.forEach(timeStuff => {
          chartData.labels.push(timeStuff.date);
          chartData.datasets[0].data.push(timeStuff.hours);
        });

        setChartDataTimeSpent(chartData);

        if (!atMount)
          setContext({
            ...context,
            toast: util.getToast('Chart updated', 'info')
          });
      }
    );
  }, [selectedPeriod]);

  return (
    <div className="component-chart">
      {/* time spent chart */}
      {chartDataTimeSpent &&
        <Card sx={{ width: '100%' }}>
          <CardContent>
            {/* card header */}
            <Grid container spacing={2}>
              <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center' }}>
                <ProfileHeader
                  noAction
                  title="Hours Sweat"
                  layout={[4, 8]}
                  buttonGroup={buttonGroupRange} />
              </Grid>
            </Grid>

            {/* chart.js */}
            <Bar ref={refChart} sx={{ mt: 1.5 }} data={chartDataTimeSpent} />
          </CardContent>
        </Card>
      }
    </div>
  );
}

export default Chart;
